package model;

import sun.util.calendar.BaseCalendar;

import java.sql.Time;

public class Flight {
    private String flightNumber;
    private String departs;
    private String departureDate;
    private String departure;
    private String arrives;
    private String arrivalDate;
    private String arrival;
    private String ecoPrice;
    private String ecoSeats;
    private String bussPrice;
    private String bussSeats;
    private String fclassPrice;
    private String fclassSeats;

    public Flight(String flightNumber, String departs, String departureDate, String departure, String arrives, String arrivalDate, String arrival, String ecoPrice, String ecoSeats, String bussPrice, String bussSeats, String fclassPrice, String fclassSeats) {
        this.flightNumber = flightNumber;
        this.departs = departs;
        this.departureDate = departureDate;
        this.departure = departure;
        this.arrives = arrives;
        this.arrivalDate = arrivalDate;
        this.arrival = arrival;
        this.ecoPrice = ecoPrice;
        this.ecoSeats = ecoSeats;
        this.bussPrice = bussPrice;
        this.bussSeats = bussSeats;
        this.fclassPrice = fclassPrice;
        this.fclassSeats = fclassSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparts() {
        return departs;
    }

    public void setDeparts(String departs) {
        this.departs = departs;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrives() {
        return arrives;
    }

    public void setArrives(String arrives) {
        this.arrives = arrives;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getEcoPrice() {
        return ecoPrice;
    }

    public void setEcoPrice(String ecoPrice) {
        this.ecoPrice = ecoPrice;
    }

    public String getEcoSeats() {
        return ecoSeats;
    }

    public void setEcoSeats(String ecoSeats) {
        this.ecoSeats = ecoSeats;
    }

    public String getBussPrice() {
        return bussPrice;
    }

    public void setBussPrice(String bussPrice) {
        this.bussPrice = bussPrice;
    }

    public String getBussSeats() {
        return bussSeats;
    }

    public void setBussSeats(String bussSeats) {
        this.bussSeats = bussSeats;
    }

    public String getFclassPrice() {
        return fclassPrice;
    }

    public void setFclassPrice(String fclassPrice) {
        this.fclassPrice = fclassPrice;
    }

    public String getFclassSeats() {
        return fclassSeats;
    }

    public void setFclassSeats(String fclassSeats) {
        this.fclassSeats = fclassSeats;
    }
}
