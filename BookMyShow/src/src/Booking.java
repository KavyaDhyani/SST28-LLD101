package src;

import java.util.*;

public class Booking {
    String id;
    Customer user;
    Show show;
    List<Ticket> tickets = new ArrayList<>();
    double totalPrice;
    BookingStatus status;
    Payment payment;
}