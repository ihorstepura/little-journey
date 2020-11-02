package org.vector.littlejourney.gui.dialog;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.utils.InputCheckService;
import org.vector.littlejourney.utils.DataSelector;

import javax.swing.*;
import java.awt.*;
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

    public static final int X_COORDINATE_OF_WINDOW = 800;

    public static final int Y_COORDINATE_OF_WINDOW = 300;

    public static final int WIDTH_OF_WINDOW = 1300;

    public static final int HEIGHT_OF_WINDOW = 1000;

    public static final Font TIMES_NEW_ROMAN_BOLD_30 = new Font("TimesRoman", Font.BOLD, 30);

    public static final Font TIMES_NEW_ROMAN_ITALIC_20 = new Font("TimesRoman", Font.ITALIC, 20);

    public static final String DATA_NOT_FOUND = "Данные не обнаружены";

    public GuiHandler(List<Trip> trips) {

        //dialog configurations
        setModal(true);
        setContentPane(contentPane);
        setBounds(X_COORDINATE_OF_WINDOW, Y_COORDINATE_OF_WINDOW, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);

        //add search button to dialog to search tickets
        buttonSearch.addActionListener(e -> onSearch());

        getRootPane().setDefaultButton(buttonSearch);

        arrivalStationInput.setFont(TIMES_NEW_ROMAN_BOLD_30);
        departureStationInput.setFont(TIMES_NEW_ROMAN_BOLD_30);
        minCostInput.setFont(TIMES_NEW_ROMAN_BOLD_30);
        maxCostInput.setFont(TIMES_NEW_ROMAN_BOLD_30);
        selectedTripsOutput.setFont(TIMES_NEW_ROMAN_ITALIC_20);

        this.trips = trips;
    }

    private void onSearch() {

        selectedTripsOutput.setText("");

        //Date time = editor.getModel().getDate();

        String departure = departureStationInput.getText();
        String arrival = arrivalStationInput.getText();

        String minimalCost = minCostInput.getText();
        String maximalCost = maxCostInput.getText();

        if (InputCheckService.checkFields(departure, arrival)) {

            if (InputCheckService.checkFields(minimalCost, maximalCost)) {

                selectedTripsOutput.append(DATA_NOT_FOUND);
            } else {

                try {
                    List<Trip> selectedByCost = DataSelector.selectByPrice(
                            trips, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));

                    createTripsForUser(selectedByCost);

                } catch (NumberFormatException e) {

                    selectedTripsOutput.append(DATA_NOT_FOUND);
                }
            }
        } else {

            List<Trip> selectedByRoute = DataSelector.selectByRoute(trips, departure, arrival);


            if (!minimalCost.equals("") || !maximalCost.equals("")) {
                try {

                    List<Trip> selectedByCost = DataSelector.selectByPrice(
                            selectedByRoute, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));

                    createTripsForUser(selectedByCost);

                } catch (NumberFormatException e) {

                    selectedTripsOutput.append(DATA_NOT_FOUND);
                }
            } else {

                createTripsForUser(selectedByRoute);
            }
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

        editor = new JSpinner.DateEditor(timeSpinner, "HH:mm");

        timeSpinner.setEditor(editor);
    }

    @Override
    protected void dialogInit() {

        super.dialogInit();
    }

    @Override
    public void run() {

        setVisible(true);
    }
}
