package org.vector.littlejourney.entity;

public class Route {

    private Station departure;

    private Station arrival;

    public Route(Station departure, Station arrival) {

        try {

            this.departure = departure;

            this.arrival = arrival;

        } catch(NullPointerException e) {

            e.printStackTrace();
        }
    }

    public Station getDeparture() {

        return departure;
    }

    public void setDeparture(Station departure) {

        try {
            this.departure = departure;

        } catch (NullPointerException e) {

            e.printStackTrace();
        }
    }

    public Station getArrival() {

        return arrival;
    }

    public void setArrival(Station arrival) {

       try {
           this.arrival = arrival;

       } catch (NullPointerException e) {

           e.printStackTrace();
       }
    }

    @Override
    public String toString() {

        return departure.toString().toUpperCase() + "-" + arrival.toString().toUpperCase();
    }
}
