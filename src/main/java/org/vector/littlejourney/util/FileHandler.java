package org.vector.littlejourney.util;

import org.vector.littlejourney.constant.WarningConstant;
import org.vector.littlejourney.exception.UnsupportedFileExtensionException;

import java.io.File;
import java.util.List;

public class FileHandler {

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

            throw new UnsupportedFileExtensionException(WarningConstant.FILE_NOT_SUPPORTED);
        }
    }

    public static List<List<String>> processDOCX(File file) {

        return null;
    }


    public static List<List<String>> processTXT(File file) {

        return null;
    }


    public static List<List<String>> processXLSX(File file) {

        return null;
    }
}
