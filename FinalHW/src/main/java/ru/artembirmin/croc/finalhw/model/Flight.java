package ru.artembirmin.croc.finalhw.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Авиарейс.
 */
public class Flight {
    /**
     * Идентификатор в БД.
     */
    @XmlTransient
    private int id;
    /**
     * Номер рейса.
     */
    @XmlElement
    private String flightNumber;
    /**
     * Город вылета.
     */
    @XmlElement
    private City departureCity;
    /**
     * Город прилета.
     */
    @XmlElement
    private City arrivalCity;
    /**
     * Дата вылета.
     */
    @XmlElement
    private LocalDate dateOfDeparture;
    /**
     * Время вылета.
     */
    @XmlElement
    private LocalTime timeOfDeparture;
    /**
     * Дата прилёта.
     * FIXME Возможно, не нужно.
     */
    @XmlTransient
    private LocalDate dateOfArrival;
    /**
     * Время прилёта.
     * FIXME Возможно, не нужно.
     */
    @XmlTransient
    private LocalTime timeOfArrival;

    /**
     * @param flightNumber      номер рейса
     * @param departureCityName назвние города вылета
     * @param arrivalCityName   название города прилета
     * @param dateOfDeparture   дата вылета
     * @param timeOfDeparture   время вылета
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
     * @param id                идентификатор в БД
     * @param flightNumber      номер рейса
     * @param departureCityName назвние города вылета
     * @param arrivalCityName   название города прилета
     * @param dateOfDeparture   дата вылета
     * @param timeOfDeparture   время вылета
     */
    public Flight(int id, String flightNumber, String departureCityName, String arrivalCityName,
                  LocalDate dateOfDeparture, LocalTime timeOfDeparture) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.arrivalCity = new City(arrivalCityName);
        this.departureCity = new City(departureCityName);
        this.dateOfDeparture = dateOfDeparture;
        this.timeOfDeparture = timeOfDeparture;
    }

    /**
     * @param id              идентификатор в БД
     * @param flightNumber    номер
     * @param departureCity   город вылета
     * @param arrivalCity     город прилета
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

    public String getDateOfDeparture() {
        return dateOfDeparture.format(DateTimeFormatter.ISO_DATE);
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public String getTimeOfDeparture() {
        return timeOfDeparture.format(DateTimeFormatter.ISO_TIME);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && Objects.equals(flightNumber, flight.flightNumber) && Objects.equals(
                departureCity, flight.departureCity) && Objects.equals(arrivalCity,
                                                                       flight.arrivalCity
        ) && Objects.equals(dateOfDeparture, flight.dateOfDeparture) && Objects.equals(timeOfDeparture,
                                                                                       flight.timeOfDeparture
        ) && Objects.equals(dateOfArrival, flight.dateOfArrival) && Objects.equals(timeOfArrival,
                                                                                   flight.timeOfArrival
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, departureCity, arrivalCity, dateOfDeparture, timeOfDeparture,
                            dateOfArrival,
                            timeOfArrival
        );
    }
}
