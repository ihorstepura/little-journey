package org.vector.utils;

import org.vector.Ticket;
import org.vector.mock.TicketFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DataFilter {

    private BufferedReader reader;
    private TicketFactory ticketFactory;
    private List<Ticket> tickets;

    public DataFilter() {

        ticketFactory = TicketFactory.getInstance();

        tickets = ticketFactory.generateTickets(10000);
    }

    public void filter() throws IOException {

        System.out.println("Для получения списка билетов по заданным критериям возпользуйтесь следующей навигацией: " + "\n"
                + "1 - выбрать маршрут" + "\n"
                + "2 - выбрать время в пути" + "\n"
                + "3 - выбрать диапазон стоимости" + "\n"
                + "4 - для выхода введите exit"
        );

        reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();

        switch (line) {
            case "1":
                String cityNameDep;
                String cityNameArr;

                System.out.println("Введите станцию отправления:");
                cityNameDep = reader.readLine();

                System.out.println("Введите станцию прибытия:");
                cityNameArr = reader.readLine();

                List<Ticket> ticketsForChosenRoute = DataSelector.selectByRoute(tickets, cityNameDep, cityNameArr);

                if (ticketsForChosenRoute.isEmpty()) {

                    System.out.println("По заданному маршруту билетов не найдено");

                } else {

                    System.out.println("По заданному направлению есть следующие маршруты: ");

                    describeList(ticketsForChosenRoute);
                }
                break;
            case "2":
                break;
            case "3":
                System.out.println("Введите минимальную цену");
                System.out.println("Введите максимальную цену");
                break;
            case "4":
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }
    }

    private void describeList(List<Ticket> tickets) {

        for (Ticket ticket : tickets) {

            System.out.println(ticket);
        }
    }
}
