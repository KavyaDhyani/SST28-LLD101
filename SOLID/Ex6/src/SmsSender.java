public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    protected void validate(Notification n) {
        // SMS requires a phone number to be present
        if (n.phone == null || n.phone.isEmpty()) {
            throw new IllegalArgumentException("SMS requires a phone number");
        }
    }

    @Override
    protected void doSend(Notification n) {
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
}
