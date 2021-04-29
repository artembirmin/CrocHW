package ru.artembirmin.croc.finalhw.repository.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.BaseFlightTest;
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

class FlightDatabaseRepositoryImplTest extends BaseFlightTest {

    /**
     * Репозиторий по работе с базой.
     */
    protected final FlightDatabaseRepositoryImpl databaseRepository = new FlightDatabaseRepositoryImpl(
            embeddedDataSource
    );

    public FlightDatabaseRepositoryImplTest() throws IOException, SQLException {
    }

    @BeforeEach
    void setUp() {
        databaseRepository.deleteAll();
        databaseRepository.createNewAll(flightsLists.getInitialFlights());
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
        databaseRepository.createNew(flight);
        flight.setId(9);
        expectedFlights.add(flight);
        assertEquals(expectedFlights, databaseRepository.findAll());
    }

    @Test
    void createNewAllAndFindAll() {
        databaseRepository.deleteAll();
        List<Flight> initialFlights = flightsLists.getInitialFlights();
        databaseRepository.createNewAll(initialFlights);
        assertEquals(flightsLists.getExpectedFlightsForFindAll(), databaseRepository.findAll());
        // Должны быть проставлены id в передаваемом списке
        assertEquals(flightsLists.getExpectedFlightsForFindAll(), initialFlights);
    }

    @Test
    void findById() {
        assertEquals(flightsLists.getExpectedFlightsForFindAll()
                                 .get(FIRST_POSITION_IN_LIST + 3),
                     databaseRepository.findById(FIRST_POSITION_IN_DB + 3)
        );
    }

    @Test
    void findAllWithCondition() {
        assertEquals(flightsLists.getExpectedFlightsForCondition(), databaseRepository.findAllWithCondition(
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
        databaseRepository.save(flight);
        assertEquals(databaseRepository.findById(FIRST_POSITION_IN_DB + 4), flight);
    }

    @Test
    void saveAll() {
        Flight flight4 = flightsLists.getExpectedFlightsForFindAll()
                                     .get(FIRST_POSITION_IN_LIST + 4);
        flight4.setRemark("Collapsed");
        databaseRepository.save(flight4);
        Flight flight6 = flightsLists.getExpectedFlightsForFindAll()
                                     .get(FIRST_POSITION_IN_LIST + 6);
        flight6.setRemark("Collapsed");
        databaseRepository.save(flight6);

        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.set(FIRST_POSITION_IN_LIST + 4, flight4);
        expectedFlights.set(FIRST_POSITION_IN_LIST + 6, flight6);

        databaseRepository.saveAll(Arrays.asList(flight4, flight6));
        assertEquals(expectedFlights, databaseRepository.findAll());
    }

    @Test
    void delete() {
        databaseRepository.delete(flightsLists.getExpectedFlightsForFindAll()
                                              .get(FIRST_POSITION_IN_LIST + 2));
        List<Flight> expectedFlights = flightsLists.getExpectedFlightsForFindAll();
        expectedFlights.remove(FIRST_POSITION_IN_LIST + 2);
        assertEquals(expectedFlights, databaseRepository.findAll());
    }

    @Test
    void deleteAll() {
        databaseRepository.deleteAll();
        assertEquals(Collections.emptyList(), databaseRepository.findAll());
    }
}