package ru.artembirmin.croc.finalhw.service.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.BaseFlightTest;
import ru.artembirmin.croc.finalhw.model.FlightsList;
import ru.artembirmin.croc.finalhw.repository.db.FlightDatabaseRepositoryImpl;
import ru.artembirmin.croc.finalhw.repository.file.FileRepositoryImpl;
import ru.artembirmin.croc.finalhw.service.db.FlightDatabaseServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo;

class FlightXmlFileServiceImplTest extends BaseFlightTest {

    /**
     * Репозиторий по работе с базой рейсов.
     */
    protected final FlightDatabaseRepositoryImpl databaseRepository = new FlightDatabaseRepositoryImpl(
            embeddedDataSource
    );

    /**
     * Сервис по работе с базой рейсов.
     * Нужен для получения объекта, который будет конвертироваться в XML.
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
     * Сервис по работе с конвертацией рейсов в XML.
     */
    protected final FlightXmlFileServiceImpl xmlFileService = new FlightXmlFileServiceImpl(
            converter, fileRepository
    );

    public FlightXmlFileServiceImplTest() throws IOException, SQLException {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void convertToXml() {
        FlightsList flightsList = new FlightsList(Collections.singletonList(
                databaseService.findById(FIRST_POSITION_IN_DB)));
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
                   isIdenticalTo(xmlFileService.convertToXml(flightsList.getFlightList()))
        );
    }

    @Test
    void writeXmlToFile() {
        FlightsList flightsList = new FlightsList(Collections.singletonList(databaseService.findById(
                FIRST_POSITION_IN_DB
        )));
        String xml = xmlFileService.convertToXml(flightsList.getFlightList());
        xmlFileService.writeXmlToFile(xml);
        assertThat(xml, isIdenticalTo(xmlFileService.readXmlFromFile()));
    }
}