package design_patterns.java_design_pattern_Introduction.singleton.practice;

public class TicketMaker {
    private int ticket;
    private static TicketMaker ticketMaker = new TicketMaker();
    private TicketMaker() {
        this.ticket = 1000;
    }

    public synchronized int getNextTicketNumber() {
        return ticket++;
    }

    public static TicketMaker getInstance() {
        return ticketMaker;
    }
}
