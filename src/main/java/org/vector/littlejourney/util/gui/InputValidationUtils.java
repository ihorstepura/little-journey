package org.vector.littlejourney.util.gui;

import org.vector.littlejourney.util.constant.StringConstant;

import javax.swing.*;

public class InputValidationUtils {

    private InputValidationUtils() {
    }

    public static boolean validateAll(JTextField... fields) {

        for (JTextField field : fields) {

            if (field.getText().equals(StringConstant.EMPTY)) {

                return true;
            }
        }
        return false;
    }
}
