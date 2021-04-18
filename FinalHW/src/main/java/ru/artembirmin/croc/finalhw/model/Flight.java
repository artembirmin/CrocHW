package ru.artembirmin.croc.finalhw.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Авиарейс.
 */
public class Flight {
    /**
     * Идентификатор в БД.
     */
    private int id;
    /**
     * Номер рейса.
     */
    private String flightNumber;
    /**
     * Город вылета.
     */
    private City departureCity;
    /**
     * Город прилета.
     */
    private City arrivalCity;
    /**
     * Дата вылета.
     */
    private LocalDate dateOfDeparture;
    /**
     * Время вылета.
     */
    private LocalTime timeOfDeparture;
    /**
     * Дата прилёта.
     * FIXME Возможно, не нужно.
     */
    private LocalDate dateOfArrival;
    /**
     * Время прилёта.
     * FIXME Возможно, не нужно.
     */
    private LocalTime timeOfArrival;

    public Flight(String flightNumber, City departureCity, City arrivalCity, LocalDate dateOfDeparture, LocalTime timeOfDeparture) {
        this.flightNumber = flightNumber;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
        this.dateOfDeparture = dateOfDeparture;
        this.timeOfDeparture = timeOfDeparture;
    }

    /**
     * @param flightNumber номер рейса
     * @param departureCityName назвние города вылета
     * @param arrivalCityName название города прилета
     * @param dateOfDeparture дата вылета
     * @param timeOfDeparture время вылета
     */
    public Flight(String flightNumber, String departureCityName, String arrivalCityName,
                  LocalDate dateOfDeparture, LocalTime timeOfDeparture) {
        this.flightNumber = flightNumber;
        this.arrivalCity = new City(arrivalCityName);
        this.departureCity = new City(departureCityName);
        this.dateOfDeparture = dateOfDeparture;
        this.timeOfDeparture = timeOfDeparture;
    }

    /**
     * @param id идентификатор в БД
     * @param flightNumber номер
     * @param departureCity город вылета
     * @param arrivalCity город прилета
     * @param dateOfDeparture дата вылета
     * @param timeOfDeparture время вылета
     */
    public Flight(int id, String flightNumber, City departureCity, City arrivalCity,
                  LocalDate dateOfDeparture, LocalTime timeOfDeparture) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
        this.dateOfDeparture = dateOfDeparture;
        this.timeOfDeparture = timeOfDeparture;
    }

    @Override
    public String toString() {
        return "\nFlight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity +
                ", dateOfDeparture=" + dateOfDeparture +
                ", timeOfDeparture=" + timeOfDeparture +
                ", dateOfArrival=" + dateOfArrival +
                ", timeOfArrival=" + timeOfArrival +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(LocalTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public LocalTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(LocalTime timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }
}
