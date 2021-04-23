package ru.artembirmin.croc.finalhw.data.db;

public interface FlightDBColumnNames {

    /**
     * Название столбца с идентификатором.
     */
    String ID_COL_NAME = "id";

    /**
     * Название столбца с номером рейса.
     */
    String FLIGHT_NUMBER_COL_NAME = "flightNumber";

    /**
     * Название столбца с названием города вылета.
     */
    String DEPARTURE_CITY_COL_NAME = "departureCity";

    /**
     * Название столбца с названием города прилета.
     */
    String ARRIVAL_CITY_COL_NAME = "arrivalCity";

    /**
     * Название столбца с датой вылета.
     */
    String DEPARTURE_DATE_COL_NAME = "departureDate";

    /**
     * Название столбца с временем вылета.
     */
    String DEPARTURE_TIME_COL_NAME = "departureTime";

    /**
     * Название столбца с датой прилета.
     */
    String ARRIVAL_DATE_COL_NAME = "arrivalDate";

    /**
     * Название столбца с временем прилета.
     */
    String ARRIVAL_TIME_COL_NAME = "arrivalTime";

    /**
     * Название столбца с временем вылета.
     */
    String REMARK_COL_NAME = "remark";
}
