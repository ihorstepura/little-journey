package org.vector.littlejourney.gui;

import org.vector.littlejourney.entity.Trip;
import org.vector.littlejourney.entity.exception.InvalidDurationException;
import org.vector.littlejourney.gui.component.dialog.ExceptionDialog;
import org.vector.littlejourney.gui.component.dialog.JourneyDialog;
import org.vector.littlejourney.util.constant.FileConstant;
import org.vector.littlejourney.mock.TripFactory;
import org.vector.littlejourney.service.TripHelper;
import org.vector.littlejourney.util.constant.Extension;
import org.vector.littlejourney.util.constant.TripConstant;
import org.vector.littlejourney.util.constant.duration.DurationWarning;
import org.vector.littlejourney.util.file.*;
import org.vector.littlejourney.util.file.exception.FileException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuiHandler {

    private GuiHandler() {
    }

    public static void generateGui() {

        TripFactory tripFactory = TripFactory.getInstance();

        List<Trip> trips = tripFactory.generateTrips(10_000);

        JourneyDialog mainWindow = new JourneyDialog();

        mainWindow.setTrips(trips);

        SwingUtilities.invokeLater(mainWindow);
    }

    public static List<Trip> generateFileLoader() throws FileException {

        List<Trip> trips = new ArrayList<>();

        JFileChooser fileChooser = new JFileChooser(FileConstant.CURRENT_DIRECTORY);

        fileChooser.setDialogTitle(FileConstant.FILE_FOR_LOAD);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                FileConstant.TXT_DOCX_XLSX_FILES,
                Extension.TXT.getValue(),
                Extension.DOCX.getValue(),
                Extension.XLSX.getValue());

        fileChooser.setFileFilter(filter);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            Extension extension = FileUtils.resolveExtension(file.getName());

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

            if (fileHandler != null) {

                List<List<String>> rows;
                rows = fileHandler.process(file);
                trips = TripHelper.process(rows, attributes);
            }
        }
        return trips;
    }

    private static FileHandler resolveFileHandler(Extension extension) {

        switch (extension) {
            case DOCX:
            case TXT:
                return new DocumentHandler();
            case XLSX:
                return new SpreadsheetHandler();
            default:
                return null;
        }
    }

    public static void generateFileSaver() throws FileException {

        JFileChooser fileSaver = new JFileChooser(FileConstant.CURRENT_DIRECTORY);

        fileSaver.setDialogTitle(FileConstant.FILE_TO_SAVE);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(FileConstant.TXT_DOCX_XLSX_FILES,
                Extension.TXT.getValue(),
                Extension.DOCX.getValue(),
                Extension.XLSX.getValue());

        fileSaver.setFileFilter(filter);

        int userSelection = fileSaver.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            File fileToSave = fileSaver.getSelectedFile();
            String fileName = fileToSave.getAbsolutePath();

            Extension extension = FileUtils.resolveExtension(fileName);

            FileHandler fileHandler = resolveFileHandler(extension);

            if (fileHandler != null) {

                fileHandler.write(fileName, JourneyDialog.getTrips());
            }
        }
    }

    public static void generateExceptionDialog(Dialog parent, String message) {

        ExceptionDialog exceptionDialog = new ExceptionDialog(parent, message);

        SwingUtilities.invokeLater(exceptionDialog);
    }

    public static boolean validateDuration(Date duration) throws InvalidDurationException {

        if (duration == null) {

            throw new InvalidDurationException(DurationWarning.DURATION_NOT_DEFINED);
        }
        return true;
    }
}
