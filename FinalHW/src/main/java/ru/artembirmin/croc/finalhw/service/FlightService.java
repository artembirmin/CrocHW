package ru.artembirmin.croc.finalhw.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.artembirmin.croc.finalhw.data.xml.JaxbConverter;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.model.FlightsList;
import ru.artembirmin.croc.finalhw.repository.FlightRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class FlightService implements BaseService<Flight> {
    private final FlightRepository repository;
    private final JaxbConverter converter;

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

    public List<Flight> findAllWithCondition(String cityOfDepartureName, String cityOfArrivalName,
                                             LocalDate dateOfDeparture) {
        return repository.findAllWithCondition(cityOfDepartureName, cityOfArrivalName, dateOfDeparture);
    }

    public String convertToXml(List<Flight> flights) {
        try {
            return converter.toXml(new FlightsList(flights));
        } catch (JsonProcessingException e) {
            System.out.println("Ошибка конвертации в XML: " + e.getMessage());
        }
        return "";
    }

    public void setRemark(Flight flight, String remark) {
        flight.setRemark(remark);
        save(flight);
    }

    public void writeXmlToFile(String xml){
        try {
            repository.writeToFile(xml);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public String readXmlFromFile(){
        try {
            return repository.readFromFile();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
            return "";
        }
    }
}
