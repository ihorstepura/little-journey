package org.vector.littlejourney.util.file;

import org.vector.littlejourney.util.constant.StringConstant;
import org.vector.littlejourney.exception.file.FileException;
import org.vector.littlejourney.util.constant.Extension;
import org.vector.littlejourney.util.constant.WarningConstant;

public class FileUtils {

    public static Extension resolveExtension(String fileName) throws FileException {

        String result;

        if (fileName.lastIndexOf(StringConstant.DOT) != -1 && fileName.lastIndexOf(StringConstant.DOT) != 0) {

            result = fileName.substring(fileName.lastIndexOf(StringConstant.DOT) + 1);
        } else {
            result = StringConstant.EMPTY;
        }

        switch (result) {
            case "txt":
                return Extension.TXT;
            case "docx":
                return Extension.DOCX;
            case "xlsx":
                return Extension.XLSX;
            default:
                throw new FileException(WarningConstant.FILE_NOT_SUPPORTED);
        }
    }
}
