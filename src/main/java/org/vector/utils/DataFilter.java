package org.vector.utils;

import org.vector.Ticket;
import org.vector.mock.TicketFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DataFilter {

    private final List<Ticket> tickets;

    public DataFilter() {

        TicketFactory ticketFactory = TicketFactory.getInstance();

        tickets = ticketFactory.generateTickets(1000);
    }

    public void filter() throws IOException {

        System.out.println(
                "Для получения списка билетов по заданным критериям возпользуйтесь следующей навигацией: "
                        + "\n"
                        + "1 - выбрать маршрут" + "\n"
                        + "2 - выбрать время в пути" + "\n"
                        + "3 - выбрать диапазон стоимости" + "\n"
        );

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

                    System.out.println("Список билетов по заданному маршруту:");

                    describeList(ticketsForChosenRoute);
                }
                break;
            case "2":


                break;
            case "3":

                int minPrice;
                int maxPrice;

                System.out.println("Введите минимальную цену:");
                minPrice = Integer.parseInt(reader.readLine());

                System.out.println("Введите максимальную цену:");
                maxPrice = Integer.parseInt(reader.readLine());

                List<Ticket> ticketsWithChosenPrice = DataSelector.selectByPrice(tickets, minPrice, maxPrice);

                if (ticketsWithChosenPrice.isEmpty()) {

                    System.out.println("Билетов с заданным диапазоном цены не найдено.");

                } else {

                    System.out.println("Список билетов с заданной ценой:");

                    describeList(ticketsWithChosenPrice);
                }
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
