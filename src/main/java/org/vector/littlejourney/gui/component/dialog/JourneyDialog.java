package org.vector.littlejourney.gui.component.dialog;

import org.vector.littlejourney.database.repository.TripRepository;
import org.vector.littlejourney.service.TripHelper;
import org.vector.littlejourney.util.constant.*;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.gui.GuiHandler;
import org.vector.littlejourney.service.DataFilter;
import org.vector.littlejourney.util.gui.InputValidationUtils;
import org.vector.littlejourney.util.exception.file.FileException;

import javax.swing.*;
import javax.swing.JSpinner.*;

import java.util.*;

public class JourneyDialog extends JDialog implements Runnable {

    private JPanel contentPane;

    private JTextField arrivalInput;

    private JTextField departureInput;

    private JTextField minCostInput;

    private JTextField maxCostInput;

    private JTextArea selectedTripsOutput;

    private DateEditor editor;

    private JButton saveToFileButton;

    private JButton uploadButton;

    private JButton searchButton;

    private JButton uploadDatabaseButton;

    private JSpinner timeSpinner;

    private static List<Trip> trips;

    public JourneyDialog() {

        //dialog configurations
        setModal(true);
        setContentPane(contentPane);

        setSize(JourneyDialogConstant.WIDTH_OF_WINDOW, JourneyDialogConstant.HEIGHT_OF_WINDOW);
        setLocationRelativeTo(null);

        configureGuiElements();
    }

    private void searchTrips() {

        Date time = editor.getModel().getDate();

        if (InputValidationUtils.validateAll(departureInput, arrivalInput)
                || InputValidationUtils.validateAll(minCostInput, maxCostInput)) {

            selectedTripsOutput.setText(StringConstant.EMPTY);

            selectedTripsOutput.append(WarningConstant.DATA_NOT_FOUND);

        } else {
            selectedTripsOutput.setText(StringConstant.EMPTY);

            List<Trip> mockTrips = JourneyDialog.trips;

            if (!InputValidationUtils.validateAll(departureInput) || !InputValidationUtils.validateAll(arrivalInput)) {

                String departure = departureInput.getText();
                String arrival = arrivalInput.getText();

                mockTrips = DataFilter.filterByRoute(mockTrips, departure, arrival);
            }
            if (!InputValidationUtils.validateAll(minCostInput) || !InputValidationUtils.validateAll(maxCostInput)) {

                String minimalCost = minCostInput.getText();
                String maximalCost = maxCostInput.getText();

                mockTrips = DataFilter.filterByPrice(mockTrips, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));
            }
            try {
                if (GuiHandler.validateDuration(time)) {

                    mockTrips = DataFilter.filterByTravelTime(mockTrips, time);

                    if (mockTrips.isEmpty()) {

                        selectedTripsOutput.append(WarningConstant.DATA_NOT_FOUND);
                    } else {

                        createTripsForUser(mockTrips);
                    }
                    setTrips(mockTrips);

                    saveToFileButton.setEnabled(true);
                }
            } catch (Exception exception) {

                GuiHandler.generateExceptionDialog(this, exception.getMessage());
            }
        }
    }

    private void uploadTripsFromFile() {

        List<Trip> loadedTrips;

        try {
            loadedTrips = GuiHandler.generateFileLoader();
            setTrips(loadedTrips);
            createTripsForUser(loadedTrips);

        } catch (FileException exception) {

            GuiHandler.generateExceptionDialog(this, exception.getMessage());
        }
    }

    private void uploadTripsFromDatabase() {

        createTripsForUser(TripRepository.getTrips());
    }

    private void saveTrips() {

        try {
            GuiHandler.generateFileSaver();

        } catch (FileException exception) {

            GuiHandler.generateExceptionDialog(this, exception.getMessage());
        }
    }

    public void setTrips(List<Trip> trips) {

        JourneyDialog.trips = trips;
    }

    public static List<Trip> getTrips() {

        return trips;
    }

    private void createTripsForUser(List<Trip> trips) {

        selectedTripsOutput.setText(StringConstant.EMPTY);

        for (Trip trip : trips) {

            selectedTripsOutput.append(TripHelper.getTripInformation(trip) + FormatConstant.NEW_LINE_SYMBOL);
        }
    }

    public void createUIComponents() {

        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);

        timeSpinner = new JSpinner(model);

        editor = new JSpinner.DateEditor(timeSpinner, DateConstant.DATE_FORMAT_dd_HH_mm);

        timeSpinner.setEditor(editor);
    }

    private void configureGuiElements() {

        arrivalInput.setFont(FontConstant.TIMES_NEW_ROMAN_BOLD_30);
        departureInput.setFont(FontConstant.TIMES_NEW_ROMAN_BOLD_30);

        minCostInput.setFont(FontConstant.TIMES_NEW_ROMAN_BOLD_30);
        maxCostInput.setFont(FontConstant.TIMES_NEW_ROMAN_BOLD_30);

        selectedTripsOutput.setFont(FontConstant.TIMES_NEW_ROMAN_ITALIC_20);

        searchButton.addActionListener(e -> searchTrips());
        uploadButton.addActionListener(e -> uploadTripsFromFile());
        saveToFileButton.addActionListener(e -> saveTrips());
        uploadDatabaseButton.addActionListener(e -> uploadTripsFromDatabase());

        getRootPane().setDefaultButton(searchButton);
    }

    @Override
    public void run() {

        setVisible(true);
    }
}
