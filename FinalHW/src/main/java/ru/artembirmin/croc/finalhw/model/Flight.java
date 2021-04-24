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
    private LocalDate departureDate;

    /**
     * Время вылета.
     */
    @XmlElement
    private LocalTime departureTime;

    /**
     * Дата прилёта.
     */
    @XmlElement
    private LocalDate arrivalDate;

    /**
     * Время прилёта.
     */
    @XmlElement
    private LocalTime arrivalTime;

    /**
     * Примечание, комментарий к вылету.
     */
    @XmlElement
    private String remark = "No status";

    /**
     * Конструктор по умолчанию для ковертации в XML.
     */
    public Flight() {
    }

    /**
     * Рейс без примечаний.
     *
     * @param flightNumber      номер рейса
     * @param departureCityName назвние города вылета
     * @param arrivalCityName   название города прилета
     * @param departureDate     дата вылета
     * @param departureTime     время вылета
     * @param arrivalDate       дата прилета
     * @param arrivalTime       время прилета
     */
    public Flight(String flightNumber, String departureCityName, String arrivalCityName,
                  LocalDate departureDate, LocalTime departureTime,
                  LocalDate arrivalDate, LocalTime arrivalTime) {
        this.flightNumber = flightNumber;
        this.arrivalCity = new City(arrivalCityName);
        this.departureCity = new City(departureCityName);
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
    }

    /**
     * Рейс с примечаниями.
     *
     * @param flightNumber      номер рейса
     * @param departureCityName назвние города вылета
     * @param arrivalCityName   название города прилета
     * @param departureDate     дата вылета
     * @param departureTime     время вылета
     * @param arrivalDate       дата прилета
     * @param arrivalTime       время прилета
     * @param remark            примечание, комментарий к вылету
     */
    public Flight(String flightNumber, String departureCityName, String arrivalCityName,
                  LocalDate departureDate, LocalTime departureTime,
                  LocalDate arrivalDate, LocalTime arrivalTime,
                  String remark) {
        this.flightNumber = flightNumber;
        this.arrivalCity = new City(arrivalCityName);
        this.departureCity = new City(departureCityName);
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.remark = remark;
    }

    /**
     * Рейс с примечанием и id.
     *
     * @param id                идентификатор в БД
     * @param flightNumber      номер рейса
     * @param departureCityName назвние города вылета
     * @param arrivalCityName   название города прилета
     * @param departureDate     дата вылета
     * @param departureTime     время вылета
     * @param arrivalDate       дата прилета
     * @param arrivalTime       время прилета
     * @param remark            примечание, комментарий к вылету
     */
    public Flight(int id, String flightNumber, String departureCityName, String arrivalCityName,
                  LocalDate departureDate, LocalTime departureTime,
                  LocalDate arrivalDate, LocalTime arrivalTime,
                  String remark) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.arrivalCity = new City(arrivalCityName);
        this.departureCity = new City(departureCityName);
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.remark = remark;
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

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = new City(arrivalCity);
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public void setDepartureCity(String departureCityName) {
        this.departureCity = new City(departureCityName);
    }

    public String getDepartureDate() {
        return departureDate.format(DateTimeFormatter.ISO_DATE);
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime.format(DateTimeFormatter.ISO_TIME);
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate.format(DateTimeFormatter.ISO_DATE);
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime.format(DateTimeFormatter.ISO_TIME);
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && Objects.equals(flightNumber, flight.flightNumber) && Objects.equals(
                departureCity, flight.departureCity) && Objects.equals(arrivalCity,
                                                                       flight.arrivalCity
        ) && Objects.equals(departureDate, flight.departureDate) && Objects.equals(departureTime,
                                                                                   flight.departureTime
        ) && Objects.equals(arrivalDate, flight.arrivalDate) && Objects.equals(arrivalTime,
                                                                               flight.arrivalTime
        ) && Objects.equals(remark, flight.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, departureCity, arrivalCity, departureDate, departureTime, arrivalDate,
                            arrivalTime, remark
        );
    }
}
