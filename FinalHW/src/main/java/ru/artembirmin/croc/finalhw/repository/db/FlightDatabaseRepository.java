package ru.artembirmin.croc.finalhw.repository.db;

import ru.artembirmin.croc.finalhw.model.Flight;

import java.time.LocalDate;
import java.util.List;

/**
 * Интерфейс репозитория для работы с базой данных рейсов.
 */
public interface FlightDatabaseRepository extends DatabaseRepository<Flight> {

    /**
     * Имя таблицы.
     */
    String TABLE_NAME = "flight";

    /**
     * Поиск всех рейсов с совпадением передаваемых данных. Использует условие WHERE&AND
     *
     * @param cityOfDepartureName город вылета
     * @param cityOfArrivalName   город прилета
     * @param dateOfDeparture     дата прилеа
     * @return список найденных рейсов
     */
    List<Flight> findAllWithCondition(String cityOfDepartureName, String cityOfArrivalName,
                                      LocalDate dateOfDeparture);

}
