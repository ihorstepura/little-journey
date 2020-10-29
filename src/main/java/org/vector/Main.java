package org.vector;

import org.vector.mock.TicketFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ticket> tickets = new TicketFactory().generateTickets(1);

        System.out.println(tickets);
    }
}
