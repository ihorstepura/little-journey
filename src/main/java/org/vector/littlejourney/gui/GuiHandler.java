package org.vector.littlejourney.gui;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.gui.component.dialog.JourneyDialog;
import org.vector.littlejourney.io.TripFileWriter;
import org.vector.littlejourney.mock.TripFactory;
import org.vector.littlejourney.service.TripHelper;
import org.vector.littlejourney.util.constant.Extension;
import org.vector.littlejourney.util.constant.TripConstant;
import org.vector.littlejourney.util.file.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
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

    public static List<Trip> generateFileChooser() {

        List<Trip> trips = new ArrayList<>();

        JFileChooser fileChooser = new JFileChooser(".");

        fileChooser.setDialogTitle("Choose file for loading");

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "DOCX, TXT & XLSX Documents", "docx", "txt", "xlsx");
        fileChooser.setFileFilter(filter);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            Extension extension = FileUtils.resolveExtension(file);

            List<Attribute> attributes = new ArrayList<>();

            Attribute attributeDep = new Attribute();

            attributeDep.setName(TripConstant.DEPARTURE.trim());
            attributeDep.setAllowedEmpty(false);
            attributeDep.setNecessarily(true);

            Attribute attributeArr = new Attribute();

            attributeArr.setName(TripConstant.ARRIVAL.trim());
            attributeArr.setAllowedEmpty(false);
            attributeArr.setNecessarily(true);

            Attribute attributeCost = new Attribute();

            attributeCost.setName(TripConstant.COST.trim());
            attributeCost.setAllowedEmpty(false);
            attributeCost.setNecessarily(true);

            Attribute attributeDur = new Attribute();

            attributeDur.setName(TripConstant.DURATION.trim());
            attributeDur.setAllowedEmpty(false);
            attributeDur.setNecessarily(true);

            attributes.add(attributeDep);
            attributes.add(attributeArr);
            attributes.add(attributeCost);
            attributes.add(attributeDur);

            FileHandler fileHandler = resolveFileHandler(extension);

            try {
                if (fileHandler != null) {

                    List<List<String>> rows = fileHandler.process(file);

                    trips = TripHelper.process(rows, attributes);
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return trips;
    }

    private static FileHandler resolveFileHandler(Extension extension) {

        switch (extension) {
            case DOCX:
                return new DocxFileHandler();
            case TXT:
                return new TxtFileHandler();
            case XLSX:
                return new XlsxFileHandler();
            default:
                return null;
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
                default:
                    break;
            }
        }
    }
}
