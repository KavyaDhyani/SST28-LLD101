public class EmailSender extends NotificationSender {
    private static final int MAX_EMAIL_BODY = 40;

    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    protected void validate(Notification n){}

    @Override
    public void doSend(Notification n) {

        String body = n.body;
        if (body != null && body.length() > MAX_EMAIL_BODY) {
            body = body.substring(0, MAX_EMAIL_BODY);
        }
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent");
    }
}
