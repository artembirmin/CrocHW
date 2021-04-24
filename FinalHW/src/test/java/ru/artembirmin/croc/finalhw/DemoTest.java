package ru.artembirmin.croc.finalhw;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.data.db.DataSourceProvider;
import ru.artembirmin.croc.finalhw.data.xml.JaxbConverter;
import ru.artembirmin.croc.finalhw.expected.ExpectedXml;
import ru.artembirmin.croc.finalhw.expected.FlightsLists;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.repository.FlightRepository;
import ru.artembirmin.croc.finalhw.service.FlightService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo;

public class DemoTest {

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

    private final ExpectedXml xmls = new ExpectedXml();

    public DemoTest() throws IOException, SQLException {
    }

    @Test
    void demo() {
        // Инициализируем
        service.deleteAll();
        service.createNewAll(flightsLists.getInitialFlights());
        service.writeXmlToFile(
                service.convertToXml(
                        service.findAllWithCondition(
                                "Krasnodar",
                                "Moscow",
                                LocalDate.of(2021, Month.APRIL, 22)
                        )
                )
        );
        assertThat(xmls.getExpectedXmlBeforeActions(), isIdenticalTo(service.readXmlFromFile()));

        // Добавили рейс, который
        service.createNew(
                new Flight(
                        "EBX 1337",
                        "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                        "Moscow",
                        LocalDate.of(2021, Month.APRIL, 22),
                        LocalTime.of(19, 45),
                        LocalDate.of(2021, Month.APRIL, 22),
                        LocalTime.of(23, 40),
                        "Departed"
                )
        );

        // Удалили 3й элемент
        service.delete(FIRST_POSITION_IN_DB + 2);

        // Изменили статус у 5го элемента. Он подходит под условие
        service.setRemark(service.findById(FIRST_POSITION_IN_DB + 4),
                          "Canceled"
        );

        // Изменили и сохранили 2й так, что он подходит под условие
        Flight updatedFlight = service.findById(FIRST_POSITION_IN_DB + 1);
        updatedFlight.setDepartureCity("Krasnodar");
        updatedFlight.setArrivalDate(LocalDate.of(2021, Month.APRIL, 22));
        updatedFlight.setDepartureDate(LocalDate.of(2021, Month.APRIL, 22));
        service.save(updatedFlight);

        // Проверили содержимое бызы
        assertEquals(flightsLists.getExpectedFlightsForDemo(), service.findAll());

        // Записали в файл новые данные
        service.writeXmlToFile(
                service.convertToXml(
                        service.findAllWithCondition(
                                "Krasnodar",
                                "Moscow",
                                LocalDate.of(2021, Month.APRIL, 22)
                        )
                )
        );

        // Проверили содержимое файла
        assertThat(xmls.getExpectedXmlAfterActions(), isIdenticalTo(service.readXmlFromFile()));
    }
}
