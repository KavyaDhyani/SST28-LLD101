package com.example.payments;

public class SafeCashAdapter implements PaymentGateway{

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = client;
    }

    public String charge(String user, int amount) {
        SafeCashPayment payment = client.createPayment(amount, user);
        return payment.confirm();
    }

}
