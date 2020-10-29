package org.vector.mock;

import org.vector.Route;
import org.vector.Ticket;

import java.util.*;

public class TicketFactory {

    private final RouteFactory routeFactory = new RouteFactory();

    public List<Ticket> generateTickets(int number) {

        return generateTicket(number);
    }

    private List<Ticket> generateTicket(int number) {

        List<Route> routes = routeFactory.generateRouts(number);

        List<Ticket> tickets = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {

            tickets.add(new Ticket());
        }

        for (int i = 0; i < number; i++) {

            tickets.get(i).setRoute(routes.get(i));
            tickets.get(i).setCost(randomTicketCost());
            tickets.get(i).setDuration(randomDuration());
        }


        return tickets;
    }

    private Date randomDuration() {

        Random random = new Random();

        long time = System.currentTimeMillis() + random.nextInt();

        return new Date(time);
    }

    private double randomTicketCost() {

        return 20 + (Math.random() * 1000);
    }
}