package com.example.payments;

public class FastPayAdapter implements PaymentGateway{
    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = client;
    }

    public String charge(String custId, int amountCents) {
        return client.payNow(custId, amountCents);
    }

}
