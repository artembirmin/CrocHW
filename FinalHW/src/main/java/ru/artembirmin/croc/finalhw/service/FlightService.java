package ru.artembirmin.croc.finalhw.service;

import ru.artembirmin.croc.finalhw.file.StringFileWriter;
import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.repository.FlightRepository;
import ru.artembirmin.croc.finalhw.xml.JaxbConverter;

import java.util.List;

public class FlightService implements BaseService<Flight> {
    private FlightRepository repository;
    private StringFileWriter fileWriter;
    private JaxbConverter converter;

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
}
