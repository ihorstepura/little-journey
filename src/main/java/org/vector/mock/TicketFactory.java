package org.vector.mock;

import org.vector.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketFactory {

    public List<Ticket> generateTickets(int number) {
        return generateTicket(number);
    }

    private List<Ticket> generateTicket(int number) {

        List<Ticket> tickets = new ArrayList<>(number);
        return tickets;
    }
}