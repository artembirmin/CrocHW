package ru.artembirmin.croc.finalhw.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.artembirmin.croc.finalhw.db.FlightDBColumnNames;
import ru.artembirmin.croc.finalhw.model.Flight;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с авиарейсами.
 */
public class FlightRepository implements BaseRepository<Flight>, FlightDBColumnNames {

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
                + " (" + idColName + ","
                + flightNumberColName + ","
                + cityOfDepartureColName + ","
                + cityOfArrivalColName + ","
                + dateOfDepartureColName + ","
                + timeOfDepartureColName + ","
                + remarkColName + ")"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            statement.setString(7, flight.getRemark());
            statement.execute();
            flight.setId(maxId);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            e.printStackTrace();
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

    /**
     * Поиск объекта по заданному id.
     *
     * @param id идентификатор объекта
     * @return найденный объект, если объект с этим id существует в единственном экземпляре, иначе null
     */
    @Override
    public Flight findById(int id) {
        List<Flight> flights = returningQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + idColName + " = " + id);
        return flights.size() == 1 ? flights.get(0) : null;
    }

    /**
     * Поиск всех рейсов с совпадением передаваемых данных. Использует условие WHERE&AND
     *
     * @param cityOfDepartureName город вылета
     * @param cityOfArrivalName   город прилета
     * @param dateOfDeparture     дата прилеа
     * @return список найденных рейсов
     */
    public List<Flight> findAllWithCondition(String cityOfDepartureName, String cityOfArrivalName,
                                             LocalDate dateOfDeparture) {
        return returningQuery("SELECT * FROM " + TABLE_NAME
                                      + " WHERE (" + cityOfDepartureColName + " = '" + cityOfDepartureName + "') AND ("
                                      + cityOfArrivalColName + " = '" + cityOfArrivalName + "') AND ("
                                      + dateOfDepartureColName + " = '" + dateOfDeparture.toString() + "')");
    }

    @Override
    public List<Flight> findAll() {
        return returningQuery("SELECT * FROM " + TABLE_NAME);
    }

    @Override
    public Flight save(Flight flight) {
        if (isTableContainsId(flight.getId())) {
            String sqlQuery = "UPDATE " + TABLE_NAME
                    + " SET " + flightNumberColName + " = '" + flight.getFlightNumber()
                    + "', " + cityOfDepartureColName + " = '" + flight.getDepartureCity()
                                                                      .getName()
                    + "', " + cityOfArrivalColName + " = '" + flight.getArrivalCity()
                                                                    .getName()
                    + "', " + dateOfDepartureColName + " = '" + flight.getDateOfDeparture()
                    + "', " + timeOfDepartureColName + " = '" + flight.getTimeOfDeparture()
                    + "', " + remarkColName + " = '" + flight.getRemark()
                    + "' WHERE " + idColName + " = " + flight.getId();
            voidQuery(sqlQuery);
        } else {
            createNew(flight);
        }
        return flight;
    }

    @Override
    public List<Flight> saveAll(List<Flight> flights) {
        for (Flight flight : flights) {
            save(flight);
        }
        return flights;
    }

    @Override
    public void delete(Flight flight) {
        delete(flight.getId());
    }

    @Override
    public void delete(int id) {
        voidQuery("DELETE FROM " + TABLE_NAME + " WHERE " + this.idColName + " = " + id);
    }

    @Override
    public void deleteAll() {
        maxId = 0;
        String sqlQuery = "DELETE FROM " + TABLE_NAME;
        voidQuery(sqlQuery);
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
     * Выполняет запрос SQL, результат выполнения которого ничего не возвращает.
     *
     * @param sqlQuery SQL запрос
     */
    private void voidQuery(String sqlQuery) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Возвращает результат выполнения SQL запроса. Используется c SELECT в запросе.
     *
     * @param sqlQuery SQL запрос
     * @return результат запроса
     */
    private List<Flight> returningQuery(String sqlQuery) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            List<Flight> taskList = new ArrayList<>();
            while (resultSet.next()) {
                taskList.add(
                        new Flight(
                                resultSet.getInt(idColName),
                                resultSet.getString(flightNumberColName),
                                resultSet.getString(cityOfDepartureColName),
                                resultSet.getString(cityOfArrivalColName),
                                resultSet.getDate(dateOfDepartureColName)
                                         .toLocalDate(),
                                resultSet.getTime(timeOfDepartureColName)
                                         .toLocalTime(),
                                resultSet.getString(remarkColName)

                        ));
            }
            return taskList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Проверяет, содержится ли передавыемый id в базе.
     *
     * @param id передавыемый id
     * @return true, если содержится
     */
    private boolean isTableContainsId(int id) {
        String sqlQuery = "SELECT " + this.idColName + " FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(this.idColName) == id) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return false;
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
                                + flightNumberColName + " VARCHAR(10),"
                                + cityOfDepartureColName + " VARCHAR(100), "
                                + cityOfArrivalColName + " VARCHAR(100),"
                                + dateOfDepartureColName + " DATE,"
                                + timeOfDepartureColName + " TIME, "
                                + remarkColName + " VARCHAR(50)"
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
