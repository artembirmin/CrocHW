package ru.artembirmin.croc.finalhw.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

class FlightRepositoryTest {
    private final int FIRST_POSITION_IN_DB = 1;
    private final int FIRST_POSITION_IN_LIST = 0;
    private FlightRepository repository = new FlightRepository(new DataSourceProvider().getDataSource());
    private List<Flight> expectedFlights = new ArrayList<>(Arrays.asList(

    ));

    FlightRepositoryTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.createNewAll(new ArrayList<>(Arrays.asList(
                new Flight("123",
                        "Krd",
                        "Msk",
                        LocalDate.of(2010, Month.APRIL, 23),
                        LocalTime.of(12, 12)),
                new Flight("456",
                        "Krd",
                        "Msk",
                        LocalDate.of(2010, Month.APRIL, 23),
                        LocalTime.of(12, 12)),
                new Flight("566",
                        "Krd",
                        "Msk",
                        LocalDate.of(2010, Month.APRIL, 23),
                        LocalTime.of(12, 12))
        )));
    }

    @Test
    void createNew() {

        System.out.println(repository.findAll());
    }

    @Test
    void createNewAll() {
    }

    @Test
    void findAll() {
    }
}