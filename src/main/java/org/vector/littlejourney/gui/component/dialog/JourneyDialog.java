package org.vector.littlejourney.gui.component.dialog;

import org.vector.littlejourney.util.constant.*;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.gui.GuiHandler;
import org.vector.littlejourney.service.DataFilter;
import org.vector.littlejourney.gui.util.InputValidationUtils;
import org.vector.littlejourney.util.file.XlsxFileHandler;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class JourneyDialog extends JDialog implements Runnable {

    private JPanel contentPane;

    private JTextField arrivalInput;

    private JTextField departureInput;

    private JTextField minCostInput;

    private JTextField maxCostInput;

    private JTextArea selectedTripsOutput;

    private JSpinner timeSpinner;

    private JSpinner.DateEditor editor;

    private JButton saveButton;

    private JButton uploadButton;

    private JButton searchButton;

    private JButton searchInFileButton;

    private static List<Trip> trips;

    public JourneyDialog() {

        //dialog configurations
        setModal(true);
        setContentPane(contentPane);
        setBounds(JourneyDialogConstant.X_COORDINATE_OF_WINDOW, JourneyDialogConstant.Y_COORDINATE_OF_WINDOW,
                JourneyDialogConstant.WIDTH_OF_WINDOW, JourneyDialogConstant.HEIGHT_OF_WINDOW);

        configureGuiElements();
    }

    private void searchTrips() {

        Date time = editor.getModel().getDate();

        if (InputValidationUtils.validateAll(departureInput, arrivalInput)
                || InputValidationUtils.validateAll(minCostInput, maxCostInput)) {

            selectedTripsOutput.setText(JourneyDialogConstant.OUTPUT_EMPTY);

            selectedTripsOutput.append(WarningConstant.DATA_NOT_FOUND);

        } else {

            selectedTripsOutput.setText(JourneyDialogConstant.OUTPUT_EMPTY);

            List<Trip> trips = JourneyDialog.trips;

            if (!InputValidationUtils.validateAll(departureInput) || !InputValidationUtils.validateAll(arrivalInput)) {

                String departure = departureInput.getText();
                String arrival = arrivalInput.getText();

                trips = DataFilter.filterByRoute(trips, departure, arrival);
            }

            if (!InputValidationUtils.validateAll(minCostInput) || !InputValidationUtils.validateAll(maxCostInput)) {

                String minimalCost = minCostInput.getText();
                String maximalCost = maxCostInput.getText();

                trips = DataFilter.filterByPrice(trips, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));
            }
            if (InputValidationUtils.validateAny(time)) {

                trips = DataFilter.filterByTravelTime(trips, time);

                if (trips.isEmpty()) {

                    selectedTripsOutput.append(WarningConstant.DATA_NOT_FOUND);
                } else

                    createTripsForUser(trips);

                setTrips(trips);

                saveButton.setEnabled(true);
            }
        }
    }

    private void uploadTrips() {

        GuiHandler.generateFileChooser();

        searchInFileButton.setEnabled(true);

        try {
            new XlsxFileHandler().process(new File("/home/ihor/Desktop/Trips.xlsx"));
        } catch (IOException e) {

        }

        List<Trip> trips = new ArrayList<>();


        setTrips(trips);
    }

    private void saveTrips() {

        GuiHandler.generateFileSaver();
    }

    private void searchTripsFromDoc() {

        searchTrips();
    }

    public void setTrips(List<Trip> trips) {

        JourneyDialog.trips = trips;
    }

    public static List<Trip> getTrips() {

        return trips;
    }

    private void createTripsForUser(List<Trip> trips) {

        for (Trip trip : trips) {

            selectedTripsOutput.append(trip.toString() + FormatConstant.NEW_LINE_SYMBOL);
        }
    }

    private void createUIComponents() {

        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);

        timeSpinner = new JSpinner(model);

        editor = new JSpinner.DateEditor(timeSpinner, DateConstant.DATE_FORMAT_HH_mm);

        timeSpinner.setEditor(editor);
    }

    private void configureGuiElements() {

        arrivalInput.setFont(FontConstant.TIMES_NEW_ROMAN_BOLD_30);

        departureInput.setFont(FontConstant.TIMES_NEW_ROMAN_BOLD_30);

        minCostInput.setFont(FontConstant.TIMES_NEW_ROMAN_BOLD_30);

        maxCostInput.setFont(FontConstant.TIMES_NEW_ROMAN_BOLD_30);

        selectedTripsOutput.setFont(FontConstant.TIMES_NEW_ROMAN_ITALIC_20);

        searchButton.addActionListener(e -> searchTrips());
        uploadButton.addActionListener(e -> uploadTrips());
        saveButton.addActionListener(e -> saveTrips());
        searchInFileButton.addActionListener(e -> searchTripsFromDoc());

        getRootPane().setDefaultButton(searchButton);
    }

    @Override
    public void run() {

        setVisible(true);
    }
}
