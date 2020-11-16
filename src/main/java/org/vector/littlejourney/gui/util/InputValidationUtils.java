package org.vector.littlejourney.gui.util;

import javax.swing.*;

public class InputValidationUtils {

    public static boolean validateAll(JTextField... fields) {

        for (JTextField field : fields) {

            if (field.getText().equals("")) return true;
        }
        return false;
    }
}
