package ru.artembirmin.croc.finalhw.service;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.data.db.DataSourceProvider;
import ru.artembirmin.croc.finalhw.data.xml.JaxbConverter;
import ru.artembirmin.croc.finalhw.expected.FlightsLists;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.model.FlightsList;
import ru.artembirmin.croc.finalhw.repository.FlightRepository;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo;

class FlightServiceTest {

    /**
     * Номер первого элемента в базе данных.
     */
    private final int FIRST_POSITION_IN_DB = 1;

    /**
     * Номер первого элеиента в списке.
     */
    private final int FIRST_POSITION_IN_LIST = 0;

    /**
     * Имя файла в ресурсах, куда произвоидся запись XML.
     */
    private final String fileName = "flights.xml";

    /**
     * Конвертер.
     */
    private final JaxbConverter converter = new JaxbConverter();

    /**
     * Ресурс данныз для БД.
     */
    private final EmbeddedDataSource embeddedDataSource = new DataSourceProvider().getDataSource();

    /**
     * Файл, в который будет производиться запись.
     */
    private final File file = new File(getClass().getClassLoader()
                                                 .getResource(fileName)
                                                 .getFile());

    /**
     * Репозиторий.
     */
    private final FlightRepository repository = new FlightRepository(
            embeddedDataSource,
            file
    );

    /**
     * Сервис.
     */
    private final FlightService service = new FlightService(
            repository,
            converter
    );

    /**
     * Списки ожидаемыми и инициализирующими рейсами.
     */
    private final FlightsLists flightsLists = new FlightsLists();

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
        service.setRemark(flight, "Canceled");
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
    void convertToXml() {
        FlightsList flightsList = new FlightsList(Collections.singletonList(service.findById(FIRST_POSITION_IN_DB)));
        assertThat("<flights>\n" +
                           "  <flight>\n" +
                           "    <flightNumber>SC 123</flightNumber>\n" +
                           "    <departureCity name=\"Krasnodar\"/>\n" +
                           "    <arrivalCity name=\"Moscow\"/>\n" +
                           "    <departureDate>2021-04-22</departureDate>\n" +
                           "    <departureTime>12:00:00</departureTime>\n" +
                           "    <arrivalDate>2021-04-22</arrivalDate>\n" +
                           "    <arrivalTime>15:00:00</arrivalTime>\n" +
                           "    <remark>Canceled</remark>\n" +
                           "  </flight>\n" +
                           "</flights>\n",
                   isIdenticalTo(service.convertToXml(flightsList.getFlightList()))
        );
    }

    @Test
    void setRemark() {
        Flight flight = flightsLists.getExpectedFlightsForFindAll()
                                    .get(FIRST_POSITION_IN_LIST + 4);
        flight.setRemark("Collapsed");
        service.setRemark(flight, "Collapsed");
        assertEquals(service.findById(FIRST_POSITION_IN_DB + 4), flight);
    }

    @Test
    void writeXmlToFile() {
        FlightsList flightsList = new FlightsList(Collections.singletonList(service.findById(
                FIRST_POSITION_IN_DB
        )));
        String xml = service.convertToXml(flightsList.getFlightList());
        service.writeXmlToFile(xml);
        assertThat(xml, isIdenticalTo(service.readXmlFromFile()));
    }
}
