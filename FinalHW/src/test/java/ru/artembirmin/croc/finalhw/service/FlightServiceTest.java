package ru.artembirmin.croc.finalhw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.db.DataSourceProvider;
import ru.artembirmin.croc.finalhw.file.StringFileWriter;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.repository.FlightRepository;
import ru.artembirmin.croc.finalhw.xml.JaxbConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FlightServiceTest {
    private final int FIRST_POSITION_IN_DB = 1;
    private final int FIRST_POSITION_IN_LIST = 0;
    private final FlightService service = new FlightService(
            new FlightRepository(new DataSourceProvider().getDataSource()), new StringFileWriter(),
            new JaxbConverter());
    private List<Flight> expectedFlights = new ArrayList<>(Arrays.asList(

    ));

    FlightServiceTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
        service.deleteAll();
        service.createNewAll(new ArrayList<>(Arrays.asList(
                new Flight("123",
                           "Krd",
                           "Msk",
                           LocalDate.of(2010, Month.APRIL, 22),
                           LocalTime.of(12, 12)
                ),
                new Flight("456",
                           "Krd",
                           "Msk",
                           LocalDate.of(2010, Month.APRIL, 21),
                           LocalTime.of(12, 12)
                ),
                new Flight("566",
                           "Krd",
                           "Msk",
                           LocalDate.of(2010, Month.APRIL, 22),
                           LocalTime.of(12, 12)
                )
        )));
    }


    @Test
    void convertToXml() {
        System.out.println(service.convertToXml(service.findAllWithCondition(
                "Krd",
                "Msk",
                LocalDate.of(2010, Month.APRIL, 22
                )
        )));
    }
}