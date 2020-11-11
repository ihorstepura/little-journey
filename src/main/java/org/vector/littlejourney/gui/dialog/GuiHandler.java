package org.vector.littlejourney.gui.dialog;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.io.TripFileWriter;
import org.vector.littlejourney.mock.TripFactory;
import org.vector.littlejourney.util.Extension;
import org.vector.littlejourney.util.FileAttribute;
import org.vector.littlejourney.util.FileHandler;
import org.vector.littlejourney.util.TripHelper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GuiHandler {

    public void generateGui() {

        TripFactory tripFactory = TripFactory.getInstance();

        List<Trip> trips = tripFactory.generateTrips(10_000);

        JourneyDialog gui = new JourneyDialog();
        gui.setTrips(trips);

        SwingUtilities.invokeLater(gui);
    }

    public static void generateFileChooser() {

        File file;

        int response;

        JFileChooser fileChooser = new JFileChooser(".");

        fileChooser.setDialogTitle("Choose file for loading");

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "DOCX, TXT & XLSX Documents", "docx", "txt", "xlsx");

        fileChooser.setFileFilter(filter);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {

            file = fileChooser.getSelectedFile();

            Extension extension = FileHandler.resolveExtension(file);

            List<FileAttribute> attributes = new ArrayList<>();

            List<List<String>> rows;
            List<Trip> trips;

            switch (extension) {

                case DOCX:

                    rows = FileHandler.processDOCX(file);
                    trips = TripHelper.process(rows, attributes);

                    break;

                case TXT:

                    rows = FileHandler.processTXT(file);
                    trips = TripHelper.process(rows, attributes);
                    break;

                case XLSX:

                    rows = FileHandler.processXLSX(file);
                    trips = TripHelper.process(rows, attributes);
                    break;
            }
        }
    }

    public static void generateFileSaver() {

        JFileChooser fileSaver = new JFileChooser(".");

        fileSaver.setDialogTitle("Specify a file to save");

        FileNameExtensionFilter docx = new FileNameExtensionFilter("docx", "docx");
        FileNameExtensionFilter txt = new FileNameExtensionFilter("txt", "txt");
        FileNameExtensionFilter xlsx = new FileNameExtensionFilter("xlsx", "xlsx");

        fileSaver.setFileFilter(docx);
        fileSaver.setFileFilter(txt);
        fileSaver.setFileFilter(xlsx);


        int userSelection = fileSaver.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            File fileToSave = fileSaver.getSelectedFile();

            String extension = fileSaver.getFileFilter().getDescription();
            String path = fileToSave.getAbsolutePath();

            switch (extension) {
                case "docx":
                case "txt":
                    TripFileWriter.writeTXT_DOCX(path + "." + extension, JourneyDialog.getTrips());
                    break;

                case "xlsx":
                    TripFileWriter.writeXLSX(path + "." + extension, JourneyDialog.getTrips());
                    break;
            }
        }
    }
}
