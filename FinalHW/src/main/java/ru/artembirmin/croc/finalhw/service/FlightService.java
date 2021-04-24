package ru.artembirmin.croc.finalhw.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.artembirmin.croc.finalhw.data.xml.JaxbConverter;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.model.FlightsList;
import ru.artembirmin.croc.finalhw.repository.FlightRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для работы с авиарейсами.
 */
public class FlightService implements BaseService<Flight> {
    /**
     * Репозиторий.
     */
    private final FlightRepository repository;
    /**
     * Конвертер в XML.
     */
    private final JaxbConverter converter;

    /**
     * @param repository репозиторий
     * @param converter  конвертер
     */
    public FlightService(FlightRepository repository,
                         JaxbConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Flight findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Flight> findAll() {
        return repository.findAll();
    }

    @Override
    public Flight save(Flight flight) {
        return repository.save(flight);
    }

    @Override
    public List<Flight> saveAll(List<Flight> flights) {
        return repository.saveAll(flights);
    }

    @Override
    public void delete(Flight flight) {
        repository.delete(flight);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Flight createNew(Flight flight) {
        return repository.createNew(flight);
    }

    @Override
    public List<Flight> createNewAll(List<Flight> flights) {
        return repository.createNewAll(flights);
    }


    /**
     * Поиск всех рейсов с совпадением передаваемых данных. Использует условие WHERE&AND
     *
     * @param cityOfDepartureName город вылета
     * @param cityOfArrivalName   город прилета
     * @param dateOfDeparture     дата прилеа
     * @return список найденных рейсов
     */
    public List<Flight> findAllWithCondition(String cityOfDepartureName, String cityOfArrivalName,
                                             LocalDate dateOfDeparture) {
        return repository.findAllWithCondition(cityOfDepartureName, cityOfArrivalName, dateOfDeparture);
    }

    /**
     * Конвертирует переданный список рейсов в XML.
     *
     * @param flights список рейсов
     * @return XML, иначе "", в случае ошибки конвертации
     */
    public String convertToXml(List<Flight> flights) {
        try {
            return converter.toXml(new FlightsList(flights));
        } catch (JsonProcessingException e) {
            System.out.println("Ошибка конвертации в XML: " + e.getMessage());
        }
        return "";
    }

    /**
     * Сетит примечание переданному рейсу и сохраняет.
     *
     * @param flight рейс
     * @param remark примечание
     */
    public void setRemark(Flight flight, String remark) {
        flight.setRemark(remark);
        save(flight);
    }

    /**
     * Записывает XML в файл.
     *
     * @param xml XML
     */
    public void writeXmlToFile(String xml) {
        try {
            repository.writeToFile(xml);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    /**
     * Читает XML из файла.
     *
     * @return XML
     */
    public String readXmlFromFile() {
        try {
            return repository.readFromFile();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
            return "";
        }
    }
}
