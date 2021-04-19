package ru.artembirmin.croc.finalhw;

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
    private final List<Flight> expectedFlightsForCondition = new ArrayList<>(Arrays.asList(
            new Flight(1,
                       "SC 123",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(12, 0)
            ),
            new Flight(5,
                       "AX 228",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 45)
            ),
            new Flight(8,
                       "BX 137",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(20, 10)
            )
    ));
    /**
     * Список ожидаемых рейсов после запроса всего содержимого базы.
     */
    private final List<Flight> expectedFlightsForFindAll = new ArrayList<>(Arrays.asList(
            new Flight(1, "SC 123",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(12, 0)
            ),
            new Flight(2, "VF 456",
                       "Adler",// Адлер - Москва. 21 апреля.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(12, 30)
            ),
            new Flight(3, "UI 566",
                       "Krasnodar",// Краснодар - Адлер. 22 апреля.
                       "Adler",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(17, 40)
            ),
            new Flight(4, "UX 756",
                       "Moscow",// Москва - Краснодар. 22 апреля. Другой порядок городов.
                       "Krasnodar",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(14, 15)
            ),
            new Flight(5, "AX 228",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 45)
            ),
            new Flight(6, "AX 228",
                       "Krasnodar",// Краснодар - Москва. 21 апреля. Другой день. Тот же рейс.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(9, 30)
            ),
            new Flight(7, "BX 137",
                       "Krasnodar",// Краснодар - Москва. 21 апреля. Другой год.
                       "Moscow",
                       LocalDate.of(2020, Month.APRIL, 22),
                       LocalTime.of(4, 50)
            ),
            new Flight(8, "BX 137",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(20, 10)
            )
    ));
    /**
     * Инициализирующий список рейсов.
     */
    private final List<Flight> initialFlights = new ArrayList<>(Arrays.asList(
            new Flight("SC 123",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(12, 0)
            ),
            new Flight("VF 456",
                       "Adler",// Адлер - Москва. 21 апреля.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(12, 30)
            ),
            new Flight("UI 566",
                       "Krasnodar",// Краснодар - Адлер. 22 апреля.
                       "Adler",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(17, 40)
            ),
            new Flight("UX 756",
                       "Moscow",// Москва - Краснодар. 22 апреля. Другой порядок городов.
                       "Krasnodar",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(14, 15)
            ),
            new Flight("AX 228",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(19, 45)
            ),
            new Flight("AX 228",
                       "Krasnodar",// Краснодар - Москва. 21 апреля. Другой день. Тот же рейс.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 21),
                       LocalTime.of(9, 30)
            ),
            new Flight("BX 137",
                       "Krasnodar",// Краснодар - Москва. 21 апреля. Другой год.
                       "Moscow",
                       LocalDate.of(2020, Month.APRIL, 22),
                       LocalTime.of(4, 50)
            ),
            new Flight("BX 137",
                       "Krasnodar",// Краснодар - Москва. 22 апреля. Искомый.
                       "Moscow",
                       LocalDate.of(2021, Month.APRIL, 22),
                       LocalTime.of(20, 10)
            )
    ));

    public List<Flight> getExpectedFlightsForCondition() {
        return expectedFlightsForCondition;
    }

    public List<Flight> getExpectedFlightsForFindAll() {
        return expectedFlightsForFindAll;
    }

    public List<Flight> getInitialFlights() {
        return initialFlights;
    }

}
