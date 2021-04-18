package ru.artembirmin.croc.finalhw.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.artembirmin.croc.finalhw.model.City;
import ru.artembirmin.croc.finalhw.model.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с авиарейсами.
 */
public class FlightRepository implements BaseRepository<Flight> {

    /**
     * Имя таблицы.
     */
    private static final String TABLE_NAME = "flight";

    /**
     * Максимальный идентификатор.
     */
    private static int maxId;

    /**
     * Ресурс данных.
     */
    private final EmbeddedDataSource dataSource;

    /**
     * Название столбца с идентификатором.
     */
    private final String idColName = "id";

    /**
     * Название столбца с номером рейса.
     */
    private final String flightNumberColName = "flightNumber";

    /**
     * Название столбца с названием города вылета.
     */
    private final String cityOfDepartureColName = "cityOfDeparture";

    /**
     * Название столбца с названием города прилета.
     */
    private final String cityOfArrivalColName = "cityOfArrival";

    /**
     * Название столбца с датой вылета.
     */
    private final String dateOfDepartureColName = "dateOfDepartureColName";

    /**
     * Название столбца с временем вылета.
     */
    private final String timeOfDepartureColName = "timeOfDepartureColName";

    /**
     * @param dataSource ресурс
     * @throws SQLException если во время нахождения maxId произошла ошибка
     */
    public FlightRepository(EmbeddedDataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        initTable();
        maxId = findMaxId();
    }

    @Override
    public Flight createNew(Flight flight) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME
                + " (" + idColName + "," + flightNumberColName + "," + cityOfDepartureColName + "," + cityOfArrivalColName + "," + dateOfDepartureColName + "," + timeOfDepartureColName + ")"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, ++maxId);
            statement.setString(2, flight.getFlightNumber());
            statement.setString(3, flight.getDepartureCity()
                                         .getName());
            statement.setString(4, flight.getArrivalCity()
                                         .getName());
            statement.setDate(5, Date.valueOf(flight.getDateOfDeparture()));
            statement.setTime(6, Time.valueOf(flight.getTimeOfDeparture()));
            statement.execute();
            flight.setId(maxId);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return flight;
    }

    @Override
    public List<Flight> createNewAll(List<Flight> flights) {
        for (Flight flight : flights) {
            createNew(flight);
        }
        return flights;
    }

    @Override
    public Flight findById(int id) {
        return null;
    }

    @Override
    public List<Flight> findAll() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            List<Flight> taskList = new ArrayList<>();
            while (resultSet.next()) {
                taskList.add(
                        new Flight(
                                resultSet.getInt(idColName),
                                resultSet.getString(flightNumberColName),
                                new City(resultSet.getString(cityOfArrivalColName)),
                                new City(resultSet.getString(cityOfDepartureColName)),
                                resultSet.getDate(dateOfDepartureColName)
                                         .toLocalDate(),
                                resultSet.getTime(timeOfDepartureColName)
                                         .toLocalTime()
                        ));
            }
            return taskList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Flight save(Flight obj) {
        return null;
    }

    @Override
    public List<Flight> saveAll(List<Flight> objects) {
        return null;
    }

    @Override
    public void delete(Flight obj) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {
        maxId = 0;
        String sqlQuery = "DELETE FROM " + TABLE_NAME;
        executeSqlCommand(sqlQuery);
    }

    /**
     * Возвращает максимальный id в базе.
     *
     * @return максимальный id в базе
     * @throws SQLException если при попытке поиска idColName выбрасывается исключение, метод прокидывает его дальше
     */
    private int findMaxId() throws SQLException {
        String sqlQuery = "SELECT MAX(" + idColName + ") FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Выполняет запрос sql, результат выполнения которого ничего не возвращает.
     *
     * @param sqlQuery SQL запрос
     */
    private void executeSqlCommand(String sqlQuery) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Метод инициализации БД.
     */
    private void initTable() {
        System.out.println(String.format("Start initializing %s table", TABLE_NAME));
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    // Несмотря на то, что мы создаем таблицу в нижнем регистре (и дальше к ней так же обращаемся),
                    // поиск мы осуществляем в верхнем. Такие вот приколы
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"}
            );
            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + idColName + " INTEGER, "
                                + flightNumberColName + " VARCHAR(50),"
                                + cityOfDepartureColName + " VARCHAR(70), "
                                + cityOfArrivalColName + " VARCHAR(70),"
                                + dateOfDepartureColName + " DATE,"
                                + timeOfDepartureColName + " TIME"
                                + ")");
                System.out.println("Table was successfully initialized");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during table initializing: " + e.getMessage());
        } finally {
            System.out.println("===========================================");
        }
    }
}
