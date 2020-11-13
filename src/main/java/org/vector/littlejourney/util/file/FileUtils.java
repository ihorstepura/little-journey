package org.vector.littlejourney.util.file;

import org.vector.littlejourney.exception.UnsupportedFileExtensionException;
import org.vector.littlejourney.gui.component.dialog.ExceptionDialog;
import org.vector.littlejourney.util.constant.Extension;
import org.vector.littlejourney.util.constant.WarningConstant;

import javax.swing.*;
import java.io.File;

public class FileUtils {

    public static Extension resolveExtension(File file) {

        String fileName = file.getName();

        String result;

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {

            result = fileName.substring(fileName.lastIndexOf(".") + 1);

        } else {

            result = "";
        }

        if (result.equalsIgnoreCase(Extension.DOCX.toString())) {

            return Extension.DOCX;

        } else if (result.equalsIgnoreCase(Extension.TXT.toString())) {

            return Extension.TXT;

        } else if (result.equalsIgnoreCase(Extension.XLSX.toString())) {

            return Extension.XLSX;

        } else {
            SwingUtilities.invokeLater(new ExceptionDialog(WarningConstant.FILE_NOT_SUPPORTED));

            throw new UnsupportedFileExtensionException(WarningConstant.FILE_NOT_SUPPORTED);
        }
    }
}
