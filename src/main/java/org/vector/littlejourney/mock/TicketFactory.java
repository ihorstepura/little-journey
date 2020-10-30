package org.vector.littlejourney.mock;

import org.vector.littlejourney.Route;
import org.vector.littlejourney.Ticket;

import java.util.*;

public class TicketFactory {

    private static TicketFactory instance;

    private final RouteFactory routeFactory = RouteFactory.getInstance();

    private TicketFactory() {

    }

    public static TicketFactory getInstance() {

        if (instance == null) {

            instance = new TicketFactory();
        }
        return instance;
    }

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
            tickets.get(i).setCost(randomCost());
            tickets.get(i).setDuration(randomDuration());
        }

        return tickets;
    }

    private Date randomDuration() {

        return DataRandomizer.randomDuration();
    }

    private double randomCost() {

        return DataRandomizer.randomTicketCost();
    }
}