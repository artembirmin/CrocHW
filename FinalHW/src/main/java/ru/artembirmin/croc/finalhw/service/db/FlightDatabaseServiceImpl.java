package ru.artembirmin.croc.finalhw.service.db;

import ru.artembirmin.croc.finalhw.model.Flight;
import ru.artembirmin.croc.finalhw.repository.db.FlightDatabaseRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для работы с базой данных рейсов.
 */
public class FlightDatabaseServiceImpl implements FlightDatabaseService {

    /**
     * Репозиторий для работы с базой данных рейсов.
     */
    private final FlightDatabaseRepository repository;

    /**
     * @param repository репозиторий для работы с базой данных рейсов
     */
    public FlightDatabaseServiceImpl(FlightDatabaseRepository repository) {
        this.repository = repository;
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

    @Override
    public List<Flight> findAllWithCondition(String cityOfDepartureName, String cityOfArrivalName,
                                             LocalDate dateOfDeparture) {
        return repository.findAllWithCondition(cityOfDepartureName, cityOfArrivalName, dateOfDeparture);
    }

    @Override
    public void setRemark(Flight flight, String remark) {
        flight.setRemark(remark);
        save(flight);
    }
}
