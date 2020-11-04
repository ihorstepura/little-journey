package org.vector.littlejourney;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.gui.dialog.GuiHandler;
import org.vector.littlejourney.mock.TripFactory;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        TripFactory tripFactory = TripFactory.getInstance();

        List<Trip> trips = tripFactory.generateTickets(10_000);

        GuiHandler gui = new GuiHandler(trips);

        Thread thread = new Thread(gui);
        thread.start();

        SwingUtilities.invokeLater(gui);
    }
}