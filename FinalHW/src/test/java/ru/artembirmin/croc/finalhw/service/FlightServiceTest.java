package ru.artembirmin.croc.finalhw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.FlightsLists;
import ru.artembirmin.croc.finalhw.db.DataSourceProvider;
import ru.artembirmin.croc.finalhw.file.StringFileWriter;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.model.FlightsList;
import ru.artembirmin.croc.finalhw.repository.FlightRepository;
import ru.artembirmin.croc.finalhw.xml.JaxbConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightServiceTest {
    private final int FIRST_POSITION_IN_DB = 1;
    private final int FIRST_POSITION_IN_LIST = 0;
    JaxbConverter converter = new JaxbConverter();
    private final FlightService service = new FlightService(
            new FlightRepository(new DataSourceProvider().getDataSource()), new StringFileWriter(),
            converter
    );
    private FlightsLists flightsLists = new FlightsLists();
    private List<Flight> expectedFlights = new ArrayList<>(Arrays.asList(

    ));

    FlightServiceTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
        service.deleteAll();
        service.createNewAll(flightsLists.getInitialFlights()
        );
    }

    @Test
    void createNew() {
        Flight flight = new Flight("BB 190",
                                   "Krasnodar",
                                   "Adler",
                                   LocalDate.of(2021, Month.MARCH, 22),
                                   LocalTime.of(20, 10),
                                   LocalDate.of(2021, Month.MARCH, 22),
                                   LocalTime.of(22, 10)
        );
        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        service.createNew(flight);
        flight.setId(9);
        expectedFlights.add(flight);
        assertEquals(expectedFlights, service.findAll());
    }

    @Test
    void createNewAllAndFindAll() {
        service.deleteAll();
        List<Flight> initialFlights = flightsLists.getInitialFlights();
        service.createNewAll(initialFlights);
        assertEquals(flightsLists.getExpectedFlightsForFindAll(), service.findAll());
        // Должны быть проставлены id в передаваемом списке
        assertEquals(flightsLists.getExpectedFlightsForFindAll(), initialFlights);
    }

    @Test
    void findById() {
        assertEquals(flightsLists.getExpectedFlightsForFindAll()
                                 .get(FIRST_POSITION_IN_LIST + 3),
                     service.findById(FIRST_POSITION_IN_DB + 3)
        );
    }

    @Test
    void findAllWithCondition() {
        assertEquals(flightsLists.getExpectedFlightsForCondition(), service.findAllWithCondition(
                "Krasnodar",
                "Moscow",
                LocalDate.of(2021, Month.APRIL, 22)
        ));
    }

    @Test
    void save() {
        Flight flight = flightsLists.getExpectedFlightsForFindAll()
                                    .get(FIRST_POSITION_IN_LIST + 4);
        flight.setRemark("Collapsed");
        service.save(flight);
        assertEquals(service.findById(FIRST_POSITION_IN_DB + 4), flight);
    }

    @Test
    void saveAll() {
        Flight flight4 = flightsLists.getExpectedFlightsForFindAll()
                                     .get(FIRST_POSITION_IN_LIST + 4);
        flight4.setRemark("Collapsed");
        service.save(flight4);
        Flight flight6 = flightsLists.getExpectedFlightsForFindAll()
                                     .get(FIRST_POSITION_IN_LIST + 6);
        flight6.setRemark("Collapsed");
        service.save(flight6);

        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.set(FIRST_POSITION_IN_LIST + 4, flight4);
        expectedFlights.set(FIRST_POSITION_IN_LIST + 6, flight6);

        service.saveAll(Arrays.asList(flight4, flight6));
        assertEquals(expectedFlights, service.findAll());
    }

    @Test
    void setStatus() {
        Flight flight = service.findById(FIRST_POSITION_IN_DB + 2);
        service.setRemark("Canceled", flight);
        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.get(FIRST_POSITION_IN_LIST + 2)
                       .setRemark("Canceled");
        assertEquals(expectedFlights, service.findAll());
    }

    @Test
    void delete() {
        service.delete(flightsLists.getExpectedFlightsForFindAll()
                                   .get(FIRST_POSITION_IN_LIST + 2));
        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.remove(FIRST_POSITION_IN_LIST + 2);
        assertEquals(expectedFlights, service.findAll());
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        assertEquals(Collections.emptyList(), service.findAll());
    }

    @Test
    void convertToXml() throws IOException {
        FlightsList flightsList = new FlightsList(service.findAllWithCondition(
                "Krasnodar",
                "Moscow",
                LocalDate.of(2021, Month.APRIL, 22)
        ));
        System.out.println(service.convertToXml(flightsList.getFlightList()));
        //assertEquals(flightsList, converter.fromXml(xml, FlightsList.class));
    }
}