package ru.artembirmin.croc.finalhw.service.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.BaseFlightTest;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.repository.db.FlightDatabaseRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightDatabaseServiceImplTest extends BaseFlightTest {

    /**
     * Репозиторий по работе с базой.
     */
    protected final FlightDatabaseRepositoryImpl databaseRepository = new FlightDatabaseRepositoryImpl(
            embeddedDataSource
    );

    /**
     * Сервис по работе с базой.
     */
    protected final FlightDatabaseServiceImpl databaseService = new FlightDatabaseServiceImpl(
            databaseRepository
    );

    FlightDatabaseServiceImplTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
        databaseService.deleteAll();
        databaseService.createNewAll(flightsLists.getInitialFlights()
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
        databaseService.createNew(flight);
        flight.setId(9);
        expectedFlights.add(flight);
        assertEquals(expectedFlights, databaseService.findAll());
    }

    @Test
    void createNewAllAndFindAll() {
        databaseService.deleteAll();
        List<Flight> initialFlights = flightsLists.getInitialFlights();
        databaseService.createNewAll(initialFlights);
        assertEquals(flightsLists.getExpectedFlightsForFindAll(), databaseService.findAll());
        // Должны быть проставлены id в передаваемом списке
        assertEquals(flightsLists.getExpectedFlightsForFindAll(), initialFlights);
    }

    @Test
    void findById() {
        assertEquals(flightsLists.getExpectedFlightsForFindAll()
                                 .get(FIRST_POSITION_IN_LIST + 3),
                     databaseService.findById(FIRST_POSITION_IN_DB + 3)
        );
    }

    @Test
    void findAllWithCondition() {
        assertEquals(flightsLists.getExpectedFlightsForCondition(), databaseService.findAllWithCondition(
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
        databaseService.save(flight);
        assertEquals(databaseService.findById(FIRST_POSITION_IN_DB + 4), flight);
    }

    @Test
    void saveAll() {
        Flight flight4 = flightsLists.getExpectedFlightsForFindAll()
                                     .get(FIRST_POSITION_IN_LIST + 4);
        flight4.setRemark("Collapsed");
        databaseService.save(flight4);
        Flight flight6 = flightsLists.getExpectedFlightsForFindAll()
                                     .get(FIRST_POSITION_IN_LIST + 6);
        flight6.setRemark("Collapsed");
        databaseService.save(flight6);

        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.set(FIRST_POSITION_IN_LIST + 4, flight4);
        expectedFlights.set(FIRST_POSITION_IN_LIST + 6, flight6);

        databaseService.saveAll(Arrays.asList(flight4, flight6));
        assertEquals(expectedFlights, databaseService.findAll());
    }

    @Test
    void setStatus() {
        Flight flight = databaseService.findById(FIRST_POSITION_IN_DB + 2);
        databaseService.setRemark(flight, "Canceled");
        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.get(FIRST_POSITION_IN_LIST + 2)
                       .setRemark("Canceled");
        assertEquals(expectedFlights, databaseService.findAll());
    }

    @Test
    void delete() {
        databaseService.delete(flightsLists.getExpectedFlightsForFindAll()
                                           .get(FIRST_POSITION_IN_LIST + 2));
        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.remove(FIRST_POSITION_IN_LIST + 2);
        assertEquals(expectedFlights, databaseService.findAll());
    }

    @Test
    void deleteAll() {
        databaseService.deleteAll();
        assertEquals(Collections.emptyList(), databaseService.findAll());
    }

    @Test
    void setRemark() {
        Flight flight = flightsLists.getExpectedFlightsForFindAll()
                                    .get(FIRST_POSITION_IN_LIST + 4);
        flight.setRemark("Collapsed");
        databaseService.setRemark(flight, "Collapsed");
        assertEquals(databaseService.findById(FIRST_POSITION_IN_DB + 4), flight);
    }
}
