package src;

public class Ticket {
    String id;
    Seat seat;
    Show show;

    public Ticket(String id, Seat seat, Show show) {
        this.id = id;
        this.seat = seat;
        this.show = show;
    }
}