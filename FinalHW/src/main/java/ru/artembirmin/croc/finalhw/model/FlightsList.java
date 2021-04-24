package ru.artembirmin.croc.finalhw.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Модель-обертка списка рейсов.
 */
@XmlRootElement(name = "flights")
public class FlightsList {

    /**
     * Список рейсов.
     */
    @XmlElement(name = "flight")
    private List<Flight> flightList;

    /**
     * @param flightList список рейсов.
     */
    public FlightsList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
}
