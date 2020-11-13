package org.vector.littlejourney.gui.component.dialog;

import org.vector.littlejourney.util.constant.ExceptionDialogConstant;
import org.vector.littlejourney.util.constant.FontConstant;

import javax.swing.*;
import java.awt.*;

public class ExceptionDialog implements Runnable {

    private final JDialog dialog;

    public ExceptionDialog(String message) {

        dialog = new JDialog(new JourneyDialog(), true);
        dialog.setBounds(ExceptionDialogConstant.X_COORDINATE_OF_WINDOW, ExceptionDialogConstant.Y_COORDINATE_OF_WINDOW,
                ExceptionDialogConstant.WIDTH_OF_WINDOW, ExceptionDialogConstant.HEIGHT_OF_WINDOW);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        configureGuiElements(message);
    }

    private void configureGuiElements(String message) {

        JButton okButton = new JButton(ExceptionDialogConstant.CLOSE);
        okButton.setFont(FontConstant.TIMES_NEW_ROMAN_ITALIC_20);
        okButton.setHorizontalAlignment(SwingConstants.CENTER);
        okButton.setVerticalAlignment(SwingConstants.CENTER);

        JTextField textField = new JTextField(message);
        textField.setFont(FontConstant.TIMES_NEW_ROMAN_BOLD_30);
        textField.setHorizontalAlignment(SwingConstants.CENTER);

        dialog.getContentPane().add(BorderLayout.AFTER_LAST_LINE, okButton);
        dialog.getContentPane().add(BorderLayout.CENTER, textField);

        okButton.addActionListener(e -> dialog.setVisible(false));
    }

    @Override
    public void run() {
        dialog.setVisible(true);
    }
}
