package org.vector;

import org.vector.gui.LittleJourneyGui;
import org.vector.mock.DataRandomizer;
import org.vector.mock.TicketFactory;
import org.vector.utils.DataFilter;
import org.vector.utils.DataSelector;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        LittleJourneyGui gui = new LittleJourneyGui();
        gui.showWindow();

        /*DataFilter filter = new DataFilter();
        filter.filter();*/

        /*TicketFactory ticketFactory = TicketFactory.getInstance();

        List<Ticket> tickets = ticketFactory.generateTickets(100);

        List<Ticket> selectedByRoute = DataSelector.selectByRoute(tickets, "Львов", "Ужгород");
        List<Ticket> selectedByPrice = DataSelector.selectByPrice(tickets, 300, 400);
        List<Ticket> selectedByTime = DataSelector.selectByTravelTime(tickets,
                new Date(DataRandomizer.randomDuration().getTime()),
                new Date(DataRandomizer.randomDuration().getTime()));

        System.out.println(selectedByRoute);
        System.out.println(selectedByPrice);
        System.out.println(selectedByTime);*/
    }
}
