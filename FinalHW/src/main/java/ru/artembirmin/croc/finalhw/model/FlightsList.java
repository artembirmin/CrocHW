package ru.artembirmin.croc.finalhw.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "flights")
public class FlightsList {
    @XmlElement(name = "flight")
    private List<Flight> flightList;

    public FlightsList() {
    }

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
