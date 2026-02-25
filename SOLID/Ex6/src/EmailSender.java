public class EmailSender extends NotificationSender {
    private static final int MAX_EMAIL_BODY = 40;

    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    protected void validate(Notification n){
        if (n.body != null && n.body.length() > MAX_EMAIL_BODY) {
            throw new IllegalArgumentException("Email body exceeds max length of " + MAX_EMAIL_BODY + " characters");
        }
    }

    @Override
    public void doSend(Notification n) {
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
    }
}
