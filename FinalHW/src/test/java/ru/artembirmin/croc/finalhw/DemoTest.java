package ru.artembirmin.croc.finalhw;

import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.repository.db.FlightDatabaseRepositoryImpl;
import ru.artembirmin.croc.finalhw.repository.file.FileRepositoryImpl;
import ru.artembirmin.croc.finalhw.service.db.FlightDatabaseServiceImpl;
import ru.artembirmin.croc.finalhw.service.file.FlightXmlFileServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo;

public class DemoTest extends BaseFlightTest {

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

    /**
     * Репозиторий по работе с файлами.
     */
    protected final FileRepositoryImpl fileRepository = new FileRepositoryImpl(
            file
    );

    /**
     * Сервис по работе с файлами.
     */
    protected final FlightXmlFileServiceImpl xmlFileService = new FlightXmlFileServiceImpl(
            converter, fileRepository
    );

    public DemoTest() throws IOException, SQLException {
    }

    @Test
    void demo() {
        // Инициализируем
        databaseService.deleteAll();
        databaseService.createNewAll(flightsLists.getInitialFlights());
        xmlFileService.writeXmlToFile(
                xmlFileService.convertToXml(
                        databaseService.findAllWithCondition(
                                "Krasnodar",
                                "Moscow",
                                LocalDate.of(2021, Month.APRIL, 22)
                        )
                )
        );
        assertThat(xmls.getExpectedXmlBeforeActions(), isIdenticalTo(xmlFileService.readXmlFromFile()));

        // Добавили рейс, который
        databaseService.createNew(
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
        databaseService.delete(FIRST_POSITION_IN_DB + 2);

        // Изменили статус у 5го элемента. Он подходит под условие
        databaseService.setRemark(databaseService.findById(FIRST_POSITION_IN_DB + 4),
                                  "Canceled"
        );

        // Изменили и сохранили 2й так, что он подходит под условие
        Flight updatedFlight = databaseService.findById(FIRST_POSITION_IN_DB + 1);
        updatedFlight.setDepartureCity("Krasnodar");
        updatedFlight.setArrivalDate(LocalDate.of(2021, Month.APRIL, 22));
        updatedFlight.setDepartureDate(LocalDate.of(2021, Month.APRIL, 22));
        databaseService.save(updatedFlight);

        // Проверили содержимое бызы
        assertEquals(flightsLists.getExpectedFlightsForDemo(), databaseService.findAll());

        // Записали в файл новые данные
        xmlFileService.writeXmlToFile(
                xmlFileService.convertToXml(
                        databaseService.findAllWithCondition(
                                "Krasnodar",
                                "Moscow",
                                LocalDate.of(2021, Month.APRIL, 22)
                        )
                )
        );

        // Проверили содержимое файла
        assertThat(xmls.getExpectedXmlAfterActions(), isIdenticalTo(xmlFileService.readXmlFromFile()));
    }
}
