package ru.artembirmin.croc.finalhw.service.db;

import ru.artembirmin.croc.finalhw.model.Flight;

import java.time.LocalDate;
import java.util.List;

/**
 * Интерфейс сервиса для работы с базой данных рейсов.
 */
public interface FlightDatabaseService extends DatabaseService<Flight> {

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

    /**
     * Сетит примечание переданному рейсу и сохраняет.
     *
     * @param flight рейс
     * @param remark примечание
     */
    void setRemark(Flight flight, String remark);
}
