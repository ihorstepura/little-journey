package org.vector.gui;

import org.vector.Ticket;
import org.vector.mock.TicketFactory;
import org.vector.utils.DataSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LittleJourneyGui extends JDialog {

    private JPanel contentPane;
    private JTextField arrivalStation;
    private JTextField departureStation;
    private JButton buttonSearch;
    private JTextField minCost;
    private JTextField maxCost;
    private JTextField minTime;
    private JTextField maxTime;
    private JTextArea resultText;
    private JLabel depLabel;

    public LittleJourneyGui() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSearch);
        setBounds(1000, 400, 1300, 1000);

        Font bigFontTR = new Font("TimesRoman", Font.BOLD, 30);
        Font littleFontTR = new Font("TimesRoman", Font.ITALIC, 20);

        arrivalStation.setFont(bigFontTR);
        departureStation.setFont(bigFontTR);
        buttonSearch.setFont(bigFontTR);
        minCost.setFont(bigFontTR);
        maxCost.setFont(bigFontTR);
        minTime.setFont(bigFontTR);
        maxTime.setFont(bigFontTR);
        resultText.setFont(littleFontTR);


        buttonSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                onSearch();
            }
        });
    }

    private void onSearch() {

        List<Ticket> tickets = createRandomTickets();

        String departure = departureStation.getText();
        String arrival = arrivalStation.getText();

        String minimalCost = minCost.getText();
        String maximalCost = maxCost.getText();

        //Date minimalTime = new Date(minTime.getText());
        //Date maximalTime = new Date(maxTime.getText());


        if (departure.equals("") || arrival.equals("")) {

            if (minimalCost.equals("") || maximalCost.equals("")) {

                resultText.append("Данных не обнаружено");
            } else {

                try {

                    List<Ticket> selectedByCost = DataSelector.selectByPrice(
                            tickets, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));
                    describeList(selectedByCost);

                } catch (Exception e) {

                    resultText.append("Данных не обнаружено");
                }
            }

        } else {


            List<Ticket> selectedByRoute = DataSelector.selectByRoute(tickets, departure, arrival);
            if (!minimalCost.equals("") || !maximalCost.equals("")) {

                List<Ticket> selectedByCost = DataSelector.selectByPrice(
                        selectedByRoute, Integer.parseInt(minimalCost), Integer.parseInt(maximalCost));

                describeList(selectedByCost);
            } else {

                describeList(selectedByRoute);
            }
        }
    }


    public void showWindow() {
        LittleJourneyGui dialog = new LittleJourneyGui();
        //dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private List<Ticket> createRandomTickets() {

        List<Ticket> tickets;

        TicketFactory ticketFactory = TicketFactory.getInstance();

        tickets = ticketFactory.generateTickets(10000);

        return tickets;
    }

    private void describeList(List<Ticket> tickets) {

        for (Ticket ticket : tickets) {

            resultText.append(ticket.toString() + "\n");

        }
    }
}
