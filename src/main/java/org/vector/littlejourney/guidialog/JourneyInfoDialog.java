package org.vector.littlejourney.guidialog;

import org.vector.littlejourney.Ticket;
import org.vector.littlejourney.mock.TicketFactory;
import org.vector.littlejourney.utils.DataSelector;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JourneyInfoDialog extends JDialog {

    private JPanel contentPane;
    private JTextField arrivalStation;
    private JTextField departureStation;
    private JButton buttonSearch;
    private JTextField minCost;
    private JTextField maxCost;
    private JTextArea resultText;
    private JComboBox<String> timeList;

    private final List<Ticket> tickets;

    public JourneyInfoDialog() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSearch);
        setBounds(800, 300, 1300, 1000);

        tickets = createRandomTickets();

        Font bigFontTR = new Font("TimesRoman", Font.BOLD, 30);
        Font littleFontTR = new Font("TimesRoman", Font.ITALIC, 20);

        arrivalStation.setFont(bigFontTR);
        departureStation.setFont(bigFontTR);
        minCost.setFont(bigFontTR);
        maxCost.setFont(bigFontTR);
        resultText.setFont(littleFontTR);

        for (int i = 0; i < 10; i++) {

            timeList.addItem("0" + i + ":00");
        }

        for (int i = 10; i < 24; i++) {

            timeList.addItem(i + ":00");
        }

        buttonSearch.addActionListener(e -> onSearch());
    }

    private void onSearch() {

        resultText.setText("");

        String departure = departureStation.getText();
        String arrival = arrivalStation.getText();

        String minimalCost = minCost.getText();
        String maximalCost = maxCost.getText();

        if (departure.equals("") || arrival.equals("")) {

            if (minimalCost.equals("") || maximalCost.equals("")) {

                resultText.append("Данные не обнаружены");
            } else {

                try {

                    List<Ticket> selectedByCost = DataSelector.selectByPrice(
                            tickets, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));

                    describeList(selectedByCost);

                } catch (NumberFormatException e) {

                    resultText.append("Данные не обнаружены");
                }
            }

        } else {

            List<Ticket> selectedByRoute = DataSelector.selectByRoute(tickets, departure, arrival);

            if (!minimalCost.equals("") || !maximalCost.equals("")) {

                try {
                    List<Ticket> selectedByCost = DataSelector.selectByPrice(
                            selectedByRoute, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));

                    describeList(selectedByCost);
                } catch (NumberFormatException e) {

                    resultText.append("Данные не обнаружены");
                }
            } else {

                describeList(selectedByRoute);
            }
        }
    }


    public void showWindow() {
        JourneyInfoDialog dialog = new JourneyInfoDialog();
        dialog.setVisible(true);
        System.exit(0);
    }

    private List<Ticket> createRandomTickets() {

        List<Ticket> tickets;

        TicketFactory ticketFactory = TicketFactory.getInstance();

        tickets = ticketFactory.generateTickets(10_000);

        return tickets;
    }

    private void describeList(List<Ticket> tickets) {

        for (Ticket ticket : tickets) {

            resultText.append(ticket.toString() + "\n");
        }
    }
}
