package org.vector.utils;

import org.vector.Ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataSelector {

    public static List<Ticket> selectByRoute(List<Ticket> tickets, String departure, String arrival) {

        List<Ticket> selectedTickets = new ArrayList<>();

        for (Ticket ticket : tickets) {

            String depart = ticket.getRoute().getDeparture().getName();
            String arr = ticket.getRoute().getArrival().getName();

            if (departure.equalsIgnoreCase(depart) && arrival.equalsIgnoreCase(arr)) {

                selectedTickets.add(ticket);
            }
        }

        return selectedTickets;
    }

    public static List<Ticket> selectByPrice(List<Ticket> tickets, int minPrice, int maxPrice) {

        List<Ticket> selectedTickets = new ArrayList<>();

        for (Ticket ticket : tickets) {

            if (ticket.getCost() <= maxPrice && ticket.getCost() >= minPrice) {

                selectedTickets.add(ticket);
            }
        }

        return selectedTickets;
    }

    public static List<Ticket> selectByTravelTime(List<Ticket> tickets, Date minTime, Date maxTime) {

        List<Ticket> selectedTickets = new ArrayList<>();

        for (Ticket ticket : tickets) {

            if ((ticket.getDuration().compareTo(maxTime) < 0 || ticket.getDuration().compareTo(maxTime) == 0) &&
                    (ticket.getDuration().compareTo(minTime) > 0 || ticket.getDuration().compareTo(minTime) == 0)) {

                selectedTickets.add(ticket);

            }
        }

        return selectedTickets;
    }
}