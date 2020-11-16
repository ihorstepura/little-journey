package org.vector.littlejourney.gui.util;

import org.vector.littlejourney.util.constant.duration.DurationWarning;
import org.vector.littlejourney.entity.exception.InvalidDurationException;

import javax.swing.*;
import java.util.Date;

public class InputValidationUtils {

    public static boolean validateAll(JTextField... fields) {

        for (JTextField field : fields) {

            if (field.getText().equals("")) return true;
        }
        return false;
    }

    public static boolean validateAny(Date time) {

        if (time == null) {

            throw new InvalidDurationException(DurationWarning.DURATION_NOT_DEFINED);

        } else {
            return true;
        }
    }
}
