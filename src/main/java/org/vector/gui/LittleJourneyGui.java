package org.vector.gui;

import org.vector.Ticket;
import org.vector.mock.TicketFactory;
import org.vector.utils.DataSelector;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
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

    public LittleJourneyGui() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSearch);
        setBounds(1300, 400, 1000, 1000);

        buttonSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                onSearch();
            }
        });
    }

    private void onSearch() {

        List<Ticket> tickets = createRandomTickets();

        List<Ticket> selected = DataSelector.selectByRoute(tickets, departureStation.getText(), arrivalStation.getText());

        describeList(selected);

    }

    public void showWindow() {
        LittleJourneyGui dialog = new LittleJourneyGui();
        dialog.pack();
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

            resultText.append(ticket.toString() + "\n"); //setText(ticket.toString());

        }
    }
}
