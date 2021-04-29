package ru.artembirmin.croc.finalhw.service.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.artembirmin.croc.finalhw.data.xml.JaxbConverter;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.model.FlightsList;
import ru.artembirmin.croc.finalhw.repository.file.FileRepository;

import java.io.IOException;
import java.util.List;

/**
 * Сервис для работы с конвертацией списков рейсов в XML, записью и чтением XML из файла.
 */
public class FlightXmlFileServiceImpl implements FlightXmlFileService {

    /**
     * Конвертер в XML.
     */
    private final JaxbConverter converter;

    /**
     * Репозиторий для работы с файлами.
     */
    private final FileRepository repository;

    /**
     * @param converter  конвертер в XML
     * @param repository репозиторий для работы с файлами
     */
    public FlightXmlFileServiceImpl(JaxbConverter converter,
                                    FileRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    @Override
    public String convertToXml(List<Flight> flights) {
        try {
            return converter.toXml(new FlightsList(flights));
        } catch (JsonProcessingException e) {
            System.out.println("Ошибка конвертации в XML: " + e.getMessage());
        }
        return "";
    }

    @Override
    public void writeXmlToFile(String xml) {
        try {
            repository.writeToFile(xml);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    @Override
    public String readXmlFromFile() {
        try {
            return repository.readFromFile();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
            return "";
        }
    }
}
