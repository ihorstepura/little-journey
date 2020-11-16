package org.vector.littlejourney.gui.component.dialog;

import org.vector.littlejourney.util.constant.ButtonConstant;
import org.vector.littlejourney.util.constant.ExceptionDialogConstant;
import org.vector.littlejourney.util.constant.FontConstant;

import javax.swing.*;
import java.awt.*;

public class ExceptionDialog extends JDialog implements Runnable {

    private final JDialog dialog;

    public ExceptionDialog(Dialog parentDialog, String message) {

        dialog = new JDialog(parentDialog, true);

        dialog.setSize(ExceptionDialogConstant.WIDTH_OF_WINDOW, ExceptionDialogConstant.HEIGHT_OF_WINDOW);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        configureGuiElements(message);
    }

    private void configureGuiElements(String message) {

        JButton okButton = new JButton(ButtonConstant.CLOSE);
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
