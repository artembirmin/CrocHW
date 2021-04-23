package ru.artembirmin.croc.finalhw;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.data.db.DataSourceProvider;
import ru.artembirmin.croc.finalhw.data.xml.JaxbConverter;
import ru.artembirmin.croc.finalhw.repository.FlightRepository;
import ru.artembirmin.croc.finalhw.service.FlightService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

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
     *
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

    public DemoTest() throws IOException, SQLException {
    }


    @Test
    void demo(){
         //TODO this sht
     }
}
