package org.vector.littlejourney.gui.dialog;

import org.vector.littlejourney.constant.DateConstant;
import org.vector.littlejourney.constant.FontConstant;
import org.vector.littlejourney.constant.JourneyDialogConstant;
import org.vector.littlejourney.constant.WarningConstant;
import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.util.DataFilter;
import org.vector.littlejourney.util.InputValidationService;
import org.vector.littlejourney.util.io.DataFileReader;
import org.vector.littlejourney.util.io.DataFileWriter;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class JourneyDialog extends JDialog implements Runnable {

    private JPanel contentPane;

    private JTextField arrivalInput;

    private JTextField departureInput;

    private JTextField minCostInput;

    private JTextField maxCostInput;

    private JButton searchButton;

    private JTextArea selectedTripsOutput;

    private JSpinner timeSpinner;

    private JButton saveButton;

    private JButton uploadButton;

    private JComboBox<String> saveFormatComboBox;

    private JComboBox<String> uploadFormatComboBox;


    private JSpinner.DateEditor editor;

    private List<Trip> trips;

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

        if (InputValidationService.validateAll(time, departureInput, arrivalInput, minCostInput, maxCostInput)) {

            selectedTripsOutput.setText(JourneyDialogConstant.OUTPUT_EMPTY);

            selectedTripsOutput.append(WarningConstant.DATA_NOT_FOUND);

        } else {

            selectedTripsOutput.setText(JourneyDialogConstant.OUTPUT_EMPTY);

            List<Trip> trips = this.trips;

            if (InputValidationService.validateAll(departureInput, arrivalInput)) {

                String departure = departureInput.getText();
                String arrival = arrivalInput.getText();

                trips = DataFilter.filterByRoute(trips, departure, arrival);
            }

            if (InputValidationService.validateAll(minCostInput, maxCostInput)) {

                String minimalCost = minCostInput.getText();
                String maximalCost = maxCostInput.getText();

                trips = DataFilter.filterByPrice(trips, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));
            }

            InputValidationService.validateAll(time);

            trips = DataFilter.filterByTravelTime(trips, time);

            if (trips.isEmpty()) selectedTripsOutput.append(WarningConstant.DATA_NOT_FOUND);

            createTripsForUser(trips);

            setTrips(trips);

            saveButton.setEnabled(true);
        }
    }

    private void uploadTrips() {

        String selectedFormat = Objects.requireNonNull(saveFormatComboBox.getSelectedItem()).toString();

        if (selectedFormat.equals(".txt") || selectedFormat.equals(".docx")) {

            System.out.println(DataFileReader.read("Trips.txt", "-"));

        } else {

            DataFileReader.read("Trips.xlsx", "\t");
        }
    }

    private void saveTrips() {

        String selectedFormat = Objects.requireNonNull(saveFormatComboBox.getSelectedItem()).toString();

        switch (selectedFormat) {

            case ".txt":

                DataFileWriter.writeTXT_DOCX("Trips.txt", getTrips());
                break;
            case ".docx":

                DataFileWriter.writeTXT_DOCX("Trips.docx", getTrips());
                break;
            case ".xlsx":

                DataFileWriter.writeXLSX("Trips.xlsx", getTrips());
                break;
        }
    }

    public void setTrips(List<Trip> trips) {

        this.trips = trips;
    }

    public List<Trip> getTrips() {

        return trips;
    }

    private void createTripsForUser(List<Trip> trips) {

        for (Trip trip : trips) {

            selectedTripsOutput.append(trip.toString() + "\n");
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

        getRootPane().setDefaultButton(searchButton);

        for (String item : JourneyDialogConstant.FORMATS) {

            saveFormatComboBox.addItem(item);
            uploadFormatComboBox.addItem(item);
        }
    }

    @Override
    public void run() {

        setVisible(true);
    }
}
