package ru.artembirmin.croc.finalhw;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.artembirmin.croc.finalhw.data.db.DataSourceProvider;
import ru.artembirmin.croc.finalhw.data.xml.JaxbConverter;
import ru.artembirmin.croc.finalhw.expected.ExpectedXml;
import ru.artembirmin.croc.finalhw.expected.FlightsLists;

import java.io.File;
import java.io.IOException;

/**
 * Содержит компоненты, необходимые для инициализации репозиториев и сервисов.
 * Так же позиции первых элементов в базе и List
 */
public class BaseFlightTest {
    /**
     * Номер первого элемента в базе данных.
     */
    protected final int FIRST_POSITION_IN_DB = 1;

    /**
     * Номер первого элеиента в списке.
     */
    protected final int FIRST_POSITION_IN_LIST = 0;

    /**
     * Имя файла в ресурсах, куда произвоидся запись XML.
     */
    protected final String fileName = "flights.xml";
    /**
     * Конвертер.
     */
    protected final JaxbConverter converter = new JaxbConverter();

    /**
     * Ресурс данныз для БД.
     */
    protected final EmbeddedDataSource embeddedDataSource = new DataSourceProvider().getDataSource();

    /**
     * Файл, в который будет производиться запись.
     */
    protected final File file = new File(BaseFlightTest.class.getClassLoader()
                                                             .getResource(fileName)
                                                             .getFile());

    /**
     * Списки ожидаемыми и инициализирующими рейсами.
     */
    protected final FlightsLists flightsLists = new FlightsLists();

    /**
     * Ожидаемые значения XML.
     */
    protected final ExpectedXml xmls = new ExpectedXml();

    public BaseFlightTest() throws IOException {
    }
}
