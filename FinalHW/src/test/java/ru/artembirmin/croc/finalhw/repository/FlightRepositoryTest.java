package ru.artembirmin.croc.finalhw.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.FlightsLists;
import ru.artembirmin.croc.finalhw.db.DataSourceProvider;
import ru.artembirmin.croc.finalhw.model.Flight;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightRepositoryTest {
    private final int FIRST_POSITION_IN_DB = 1;
    private final int FIRST_POSITION_IN_LIST = 0;
    private final FlightRepository repository = new FlightRepository(new DataSourceProvider().getDataSource());
    private final FlightsLists flightsLists = new FlightsLists();

    FlightRepositoryTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.createNewAll(flightsLists.getInitialFlights());
    }

    @Test
    void createNew() {
        Flight flight = new Flight("BB 190",
                                   "Krasnodar",
                                   "Adler",
                                   LocalDate.of(2021, Month.MARCH, 22),
                                   LocalTime.of(20, 10),
                                   LocalDate.of(2021, Month.MARCH, 22),
                                   LocalTime.of(23, 10)
        );
        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        repository.createNew(flight);
        flight.setId(9);
        expectedFlights.add(flight);
        assertEquals(expectedFlights, repository.findAll());
    }

    @Test
    void createNewAllAndFindAll() {
        repository.deleteAll();
        List<Flight> initialFlights = flightsLists.getInitialFlights();
        repository.createNewAll(initialFlights);
        assertEquals(flightsLists.getExpectedFlightsForFindAll(), repository.findAll());
        // Должны быть проставлены id в передаваемом списке
        assertEquals(flightsLists.getExpectedFlightsForFindAll(), initialFlights);
    }

    @Test
    void findById() {
        assertEquals(flightsLists.getExpectedFlightsForFindAll()
                                 .get(FIRST_POSITION_IN_LIST + 3),
                     repository.findById(FIRST_POSITION_IN_DB + 3)
        );
    }

    @Test
    void findAllWithCondition() {
        assertEquals(flightsLists.getExpectedFlightsForCondition(), repository.findAllWithCondition(
                "Krasnodar",
                "Moscow",
                LocalDate.of(2021, Month.APRIL, 22
                )
        ));
    }

    @Test
    void save() {
        Flight flight = flightsLists.getExpectedFlightsForFindAll()
                                    .get(FIRST_POSITION_IN_LIST + 4);
        flight.setRemark("Collapsed");
        repository.save(flight);
        assertEquals(repository.findById(FIRST_POSITION_IN_DB + 4), flight);
    }

    @Test
    void saveAll() {
        Flight flight4 = flightsLists.getExpectedFlightsForFindAll()
                                     .get(FIRST_POSITION_IN_LIST + 4);
        flight4.setRemark("Collapsed");
        repository.save(flight4);
        Flight flight6 = flightsLists.getExpectedFlightsForFindAll()
                                     .get(FIRST_POSITION_IN_LIST + 6);
        flight6.setRemark("Collapsed");
        repository.save(flight6);

        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.set(FIRST_POSITION_IN_LIST + 4, flight4);
        expectedFlights.set(FIRST_POSITION_IN_LIST + 6, flight6);

        repository.saveAll(Arrays.asList(flight4, flight6));
        assertEquals(expectedFlights, repository.findAll());
    }

    @Test
    void delete() {
        repository.delete(flightsLists.getExpectedFlightsForFindAll()
                                      .get(FIRST_POSITION_IN_LIST + 2));
        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.remove(FIRST_POSITION_IN_LIST + 2);
        assertEquals(expectedFlights, repository.findAll());
    }

    @Test
    void deleteAll() {
        repository.deleteAll();
        assertEquals(Collections.emptyList(), repository.findAll());
    }
}