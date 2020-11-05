package org.vector.littlejourney.gui.dialog;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.mock.TripFactory;

import javax.swing.*;
import java.util.List;

public class GuiHandler {

    public void generateGui() {

        TripFactory tripFactory = TripFactory.getInstance();

        List<Trip> trips = tripFactory.generateTrips(10_000);

        JourneyDialog gui = new JourneyDialog();
        gui.setTrips(trips);

        SwingUtilities.invokeLater(gui);
    }
}
