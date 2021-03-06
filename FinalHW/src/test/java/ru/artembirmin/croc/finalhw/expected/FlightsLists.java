package ru.artembirmin.croc.finalhw.expected;

import ru.artembirmin.croc.finalhw.model.Flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Набор списков с рейсами для удобства тестирования.
 */
public class FlightsLists {

    /**
     * Список ожидаемых рейсов для запроса к базе с условием.
     */
    private final ArrayList<Flight> expectedFlightsForCondition = new ArrayList<>(Arrays.asList(
            new Flight(1,
                       "SC 123",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(12, 0),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(15, 0),
                       "Canceled"
            ),
            new Flight(5,
                       "AX 228",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 45),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(23, 40),
                       "Departed"
            ),
            new Flight(8,
                       "BX 137",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(20, 10),
                       LocalDate.of(2021, Month.APRIL, 23),
                       LocalTime.of(0, 0),
                       "No status"
            )
    ));

    /**
     * Список ожидаемых рейсов после запроса всего содержимого базы.
     */
    private final ArrayList<Flight> expectedFlightsForFindAll = new ArrayList<>(Arrays.asList(
            new Flight(1, "SC 123",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(12, 0),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(15, 0),
                       "Canceled"
            ),
            new Flight(2, "VF 456",
                       "Adler",// Адлер - Москва. 21 апреля.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(12, 30),
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(17, 0),
                       "Departed"
            ),
            new Flight(3, "UI 566",
                       "Krasnodar",// Краснодар - Адлер. 22 апреля.
                       "Adler",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(17, 40),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 0),
                       "No status"
            ),
            new Flight(4, "UX 756",
                       "Moscow",// Москва - Краснодар. 22 апреля. Другой порядок городов.
                       "Krasnodar",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(14, 15),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(17, 15),
                       "No status"
            ),
            new Flight(5, "AX 228",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 45),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(23, 40),
                       "Departed"
            ),
            new Flight(6, "AX 228",
                       "Krasnodar",// Краснодар - Москва. 21 апреля. Другой день. Тот же рейс.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(9, 30),
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(12, 30),
                       "No status"
            ),
            new Flight(7, "BX 137",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Другой год.
                       "Moscow",
                       LocalDate.of(2020, Month.APRIL, 22),
                       LocalTime.of(4, 50),
                       LocalDate.of(2020, Month.APRIL, 22),
                       LocalTime.of(7, 50),
                       "Postponed 1 year"
            ),
            new Flight(8, "BX 137",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(20, 10),
                       LocalDate.of(2021, Month.APRIL, 23),
                       LocalTime.of(0, 0),
                       "No status"
            )
    ));

    private final ArrayList<Flight> expectedFlightsForDemo = new ArrayList<>(Arrays.asList(
            new Flight(1, "SC 123",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(12, 0),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(15, 0),
                       "Canceled"
            ),
            new Flight(2, "VF 456",
                       "Krasnodar",// Адлер - Москва. 21 апреля.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(12, 30),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(17, 0),
                       "Departed"
            ),
            new Flight(4, "UX 756",
                       "Moscow",// Москва - Краснодар. 22 апреля. Другой порядок городов.
                       "Krasnodar",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(14, 15),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(17, 15),
                       "No status"
            ),
            new Flight(5, "AX 228",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 45),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(23, 40),
                       "Canceled"
            ),
            new Flight(6, "AX 228",
                       "Krasnodar",// Краснодар - Москва. 21 апреля. Другой день. Тот же рейс.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(9, 30),
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(12, 30),
                       "No status"
            ),
            new Flight(7, "BX 137",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Другой год.
                       "Moscow",
                       LocalDate.of(2020, Month.APRIL, 22),
                       LocalTime.of(4, 50),
                       LocalDate.of(2020, Month.APRIL, 22),
                       LocalTime.of(7, 50),
                       "Postponed 1 year"
            ),
            new Flight(8, "BX 137",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(20, 10),
                       LocalDate.of(2021, Month.APRIL, 23),
                       LocalTime.of(0, 0),
                       "No status"
            ),
            new Flight(9, "EBX 1337",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 45),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(23, 40),
                       "Departed"
            )
    ));

    /**
     * Инициализирующий список рейсов.
     */
    private final ArrayList<Flight> initialFlights = new ArrayList<>(Arrays.asList(
            new Flight("SC 123",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(12, 0),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(15, 0),
                       "Canceled"
            ),
            new Flight("VF 456",
                       "Adler",// Адлер - Москва. 21 апреля.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(12, 30),
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(17, 0),
                       "Departed"
            ),
            new Flight("UI 566",
                       "Krasnodar",// Краснодар - Адлер. 22 апреля.
                       "Adler",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(17, 40),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 0)
            ),
            new Flight("UX 756",
                       "Moscow",// Москва - Краснодар. 22 апреля. Другой порядок городов.
                       "Krasnodar",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(14, 15),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(17, 15)
            ),
            new Flight("AX 228",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 45),
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(23, 40),
                       "Departed"
            ),
            new Flight("AX 228",
                       "Krasnodar",// Краснодар - Москва. 21 апреля. Другой день. Тот же рейс.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(9, 30),
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(12, 30)
            ),
            new Flight("BX 137",
                       "Krasnodar",// Краснодар - Москва. 21 апреля. Другой год.
                       "Moscow",
                       LocalDate.of(2020, Month.APRIL, 22),
                       LocalTime.of(4, 50),
                       LocalDate.of(2020, Month.APRIL, 22),
                       LocalTime.of(7, 50),
                       "Postponed 1 year"
            ),
            new Flight("BX 137",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(20, 10),
                       LocalDate.of(2021, Month.APRIL, 23),
                       LocalTime.of(0, 0)
            )
    ));


    public List<Flight> getExpectedFlightsForCondition() {
        return (List<Flight>) expectedFlightsForCondition.clone();
    }

    public List<Flight> getExpectedFlightsForFindAll() {
        return (List<Flight>) expectedFlightsForFindAll.clone();
    }

    public List<Flight> getInitialFlights() {
        return (List<Flight>) initialFlights.clone();
    }

    public List<Flight> getExpectedFlightsForDemo() {
        return (List<Flight>) expectedFlightsForDemo.clone();
    }
}
