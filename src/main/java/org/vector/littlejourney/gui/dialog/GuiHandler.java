package org.vector.littlejourney.gui.dialog;

import org.vector.littlejourney.constant.DateConstants;
import org.vector.littlejourney.constant.FontConstants;
import org.vector.littlejourney.constant.GuiConstants;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DataSelector;
import org.vector.littlejourney.util.InputCheckService;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GuiHandler extends JDialog implements Runnable {

    private JPanel contentPane;

    private JTextField arrivalStationInput;

    private JTextField departureStationInput;

    private JTextField minCostInput;

    private JTextField maxCostInput;

    private JButton buttonSearch;

    private JTextArea selectedTripsOutput;

    private JSpinner timeSpinner;

    private JSpinner.DateEditor editor;

    private final List<Trip> trips;

    public GuiHandler(List<Trip> trips) {

        //dialog configurations
        setModal(true);
        setContentPane(contentPane);
        setBounds(GuiConstants.X_COORDINATE_OF_WINDOW, GuiConstants.Y_COORDINATE_OF_WINDOW,
                GuiConstants.WIDTH_OF_WINDOW, GuiConstants.HEIGHT_OF_WINDOW);

        //add search button to dialog to search tickets
        buttonSearch.addActionListener(e -> onSearch());

        getRootPane().setDefaultButton(buttonSearch);

        arrivalStationInput.setFont(FontConstants.TIMES_NEW_ROMAN_BOLD_30);
        departureStationInput.setFont(FontConstants.TIMES_NEW_ROMAN_BOLD_30);
        minCostInput.setFont(FontConstants.TIMES_NEW_ROMAN_BOLD_30);
        maxCostInput.setFont(FontConstants.TIMES_NEW_ROMAN_BOLD_30);
        selectedTripsOutput.setFont(FontConstants.TIMES_NEW_ROMAN_ITALIC_20);

        this.trips = trips;
    }

    private void onSearch() {

        selectedTripsOutput.setText("");

        // get time from timeSpinner
        Date timeInput = editor.getModel().getDate();

        // get departure and arrival from departure/arrival textFields
        String departure = departureStationInput.getText();
        String arrival = arrivalStationInput.getText();

        // get minimal and maximal time from time textFields
        String minimalCost = minCostInput.getText();
        String maximalCost = maxCostInput.getText();

        if (InputCheckService.checkFields(departure, arrival)) {

            if (InputCheckService.checkFields(minimalCost, maximalCost)) {

                selectedTripsOutput.append(GuiConstants.DATA_NOT_FOUND);
            } else {

                selectedTripsByTime(timeInput, minimalCost, maximalCost, trips);
            }
        } else {

            List<Trip> selectedByRoute = DataSelector.selectByRoute(trips, departure, arrival);

            if (!InputCheckService.checkFields(minimalCost, maximalCost)) {

                selectedTripsByTime(timeInput, minimalCost, maximalCost, selectedByRoute);

            } else {

                List<Trip> selectedByTime = DataSelector.selectByTravelTime(selectedByRoute, timeInput);

                createTripsForUser(selectedByTime);
            }
        }
    }

    private void selectedTripsByTime(Date timeInput, String minimalCost, String maximalCost, List<Trip> selectedByRoute) {
        try {

            List<Trip> selectedByCost = DataSelector.selectByPrice(
                    selectedByRoute, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));

            List<Trip> selectedByTime = DataSelector.selectByTravelTime(selectedByCost, timeInput);

            createTripsForUser(selectedByTime);

        } catch (NumberFormatException e) {

            selectedTripsOutput.append(GuiConstants.DATA_NOT_FOUND);
        }
    }

    private void createTripsForUser(List<Trip> trips) {

        for (Trip trip : trips) {

            selectedTripsOutput.append(trip.toString() + "\n");
        }
    }

    private void createUIComponents() {
        Date date = new Date();

        SpinnerDateModel model = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);

        timeSpinner = new JSpinner(model);

        editor = new JSpinner.DateEditor(timeSpinner, DateConstants.DATE_FORMAT_HH_mm);

        timeSpinner.setEditor(editor);
    }

    @Override
    public void run() {

        setVisible(true);
    }
}