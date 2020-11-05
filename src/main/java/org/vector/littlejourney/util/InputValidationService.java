package org.vector.littlejourney.util;

import org.vector.littlejourney.constant.duration.DurationWarning;
import org.vector.littlejourney.exception.duration.InvalidDurationException;

import javax.swing.*;
import java.util.Date;

public class InputValidationService {

    public static boolean validateAll(JTextField... fields) {

        for (JTextField field : fields) {

            if (field.getText().equals("")) return false;
        }
        return true;
    }

    public static boolean validateAll(Date time, JTextField... fields) {

        if (time == null) throw new InvalidDurationException(DurationWarning.DURATION_NOT_DEFINED);

        for (JTextField field : fields) {

            if (field.getText().equals("")) return true;
        }
        return false;
    }
}
