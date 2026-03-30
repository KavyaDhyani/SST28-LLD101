package src;

import java.util.*;

public class Show {
    String id;
    Movie movie;
    Screen screen;
    String startTime;

    Map<String, SeatStatus> seatStatusMap = new HashMap<>();

    public Show(String id, Movie movie, Screen screen) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;

        for (Seat seat : screen.seats) {
            seatStatusMap.put(seat.id, SeatStatus.AVAILABLE);
        }
    }
}