package ru.artembirmin.croc.finalhw.repository;

import org.junit.jupiter.api.Assertions;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightRepositoryTest {
    private final int FIRST_POSITION_IN_DB = 1;
    private final int FIRST_POSITION_IN_LIST = 0;
    private FlightRepository repository = new FlightRepository(new DataSourceProvider().getDataSource());
    private FlightsLists flightsLists = new FlightsLists();


    FlightRepositoryTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.createNewAll(flightsLists.getInitialFlights());
    }

    @Test
    void createNewAllAndFindAll() {
        assertEquals(flightsLists.getExpectedFlightsForFindAll(), repository.findAll());
    }

    @Test
    void findAllWithCondition() {
        assertEquals(flightsLists.getExpectedFlightsForCondition(), repository.findAllWithCondition(
                "Krasnodar",
                "Moscow",
                LocalDate.of(2021, Month.APRIL, 22
        )));
    }
}