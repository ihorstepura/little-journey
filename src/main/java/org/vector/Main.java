package org.vector;

import org.vector.mock.TicketFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        TicketFactory ticketFactory = TicketFactory.getInstance();

        List<Ticket> tickets = ticketFactory.generateTickets(1);

        System.out.println(tickets);
    }
}
