package ru.artembirmin.croc.finalhw.db;

public interface FlightDBColumnNames {
    /**
     * Название столбца с идентификатором.
     */
    String idColName = "id";

    /**
     * Название столбца с номером рейса.
     */
    String flightNumberColName = "flightNumber";

    /**
     * Название столбца с названием города вылета.
     */
    String cityOfDepartureColName = "cityOfDeparture";

    /**
     * Название столбца с названием города прилета.
     */
    String cityOfArrivalColName = "cityOfArrival";

    /**
     * Название столбца с датой вылета.
     */
    String dateOfDepartureColName = "dateOfDeparture";

    /**
     * Название столбца с временем вылета.
     */
    String timeOfDepartureColName = "timeOfDeparture";

    /**
     * Название столбца с временем вылета.
     */
    String remarkColName = "remark";
}
