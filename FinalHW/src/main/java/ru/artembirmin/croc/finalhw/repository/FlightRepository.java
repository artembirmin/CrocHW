package ru.artembirmin.croc.finalhw.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.artembirmin.croc.finalhw.data.db.FlightDBColumnNames;
import ru.artembirmin.croc.finalhw.data.file.ResourcesFileManager;
import ru.artembirmin.croc.finalhw.model.Flight;

import java.io.File;
import java.io.IOException;
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
     * Ресурс данных для БД.
     */
    private final EmbeddedDataSource dataSource;

    /**
     * Менеджер файлов в ресурсах.
     */
    private final ResourcesFileManager resourcesFileManager;

    /**
     * @param dataSource ресурс
     * @throws SQLException если во время нахождения maxId произошла ошибка
     */
    public FlightRepository(EmbeddedDataSource dataSource, File file) throws SQLException {
        resourcesFileManager = new ResourcesFileManager(file);
        this.dataSource = dataSource;
        initTable();
        maxId = findMaxId();
    }

    @Override
    public Flight createNew(Flight flight) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME
                + " (" + ID_COL_NAME + ","
                + FLIGHT_NUMBER_COL_NAME + ","
                + DEPARTURE_CITY_COL_NAME + ","
                + ARRIVAL_CITY_COL_NAME + ","
                + DEPARTURE_DATE_COL_NAME + ","
                + DEPARTURE_TIME_COL_NAME + ","
                + ARRIVAL_DATE_COL_NAME + ","
                + ARRIVAL_TIME_COL_NAME + ","
                + REMARK_COL_NAME + ")"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, ++maxId);
            statement.setString(2, flight.getFlightNumber());
            statement.setString(3, flight.getDepartureCity()
                                         .getName());
            statement.setString(4, flight.getArrivalCity()
                                         .getName());
            statement.setDate(5, Date.valueOf(flight.getDepartureDate()));
            statement.setTime(6, Time.valueOf(flight.getDepartureTime()));
            statement.setDate(7, Date.valueOf(flight.getArrivalDate()));
            statement.setTime(8, Time.valueOf(flight.getArrivalTime()));
            statement.setString(9, flight.getRemark());
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
        List<Flight> flights = returningQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COL_NAME + " = " + id);
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
                                      + " WHERE (" + DEPARTURE_CITY_COL_NAME + " = '" + cityOfDepartureName + "') AND ("
                                      + ARRIVAL_CITY_COL_NAME + " = '" + cityOfArrivalName + "') AND ("
                                      + DEPARTURE_DATE_COL_NAME + " = '" + dateOfDeparture.toString() + "')");
    }

    @Override
    public List<Flight> findAll() {
        return returningQuery("SELECT * FROM " + TABLE_NAME);
    }

    @Override
    public Flight save(Flight flight) {
        if (isTableContainsId(flight.getId())) {
            String sqlQuery = "UPDATE " + TABLE_NAME
                    + " SET " + FLIGHT_NUMBER_COL_NAME + " = '" + flight.getFlightNumber()
                    + "', " + DEPARTURE_CITY_COL_NAME + " = '" + flight.getDepartureCity()
                                                                       .getName()
                    + "', " + ARRIVAL_CITY_COL_NAME + " = '" + flight.getArrivalCity()
                                                                     .getName()
                    + "', " + DEPARTURE_DATE_COL_NAME + " = '" + flight.getDepartureDate()
                    + "', " + DEPARTURE_TIME_COL_NAME + " = '" + flight.getDepartureTime()
                    + "', " + ARRIVAL_DATE_COL_NAME + " = '" + flight.getArrivalDate()
                    + "', " + ARRIVAL_TIME_COL_NAME + " = '" + flight.getArrivalTime()
                    + "', " + REMARK_COL_NAME + " = '" + flight.getRemark()
                    + "' WHERE " + ID_COL_NAME + " = " + flight.getId();
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
        voidQuery("DELETE FROM " + TABLE_NAME + " WHERE " + this.ID_COL_NAME + " = " + id);
    }

    @Override
    public void deleteAll() {
        maxId = 0;
        String sqlQuery = "DELETE FROM " + TABLE_NAME;
        voidQuery(sqlQuery);
    }

    public void writeToFile(String str) throws IOException {
        resourcesFileManager.writeStringToFile(str);
    }

    public String readFromFile() throws IOException {
        return resourcesFileManager.readStringFromFile();
    }

    public File getFile() {
        return resourcesFileManager.getCurrentFile();
    }

    /**
     * Возвращает максимальный id в базе.
     *
     * @return максимальный id в базе
     * @throws SQLException если при попытке поиска idColName выбрасывается исключение, метод прокидывает его дальше
     */
    private int findMaxId() throws SQLException {
        String sqlQuery = "SELECT MAX(" + ID_COL_NAME + ") FROM " + TABLE_NAME;
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
                                resultSet.getInt(ID_COL_NAME),
                                resultSet.getString(FLIGHT_NUMBER_COL_NAME),
                                resultSet.getString(DEPARTURE_CITY_COL_NAME),
                                resultSet.getString(ARRIVAL_CITY_COL_NAME),
                                resultSet.getDate(DEPARTURE_DATE_COL_NAME)
                                         .toLocalDate(),
                                resultSet.getTime(DEPARTURE_TIME_COL_NAME)
                                         .toLocalTime(),
                                resultSet.getDate(ARRIVAL_DATE_COL_NAME)
                                         .toLocalDate(),
                                resultSet.getTime(ARRIVAL_TIME_COL_NAME)
                                         .toLocalTime(),
                                resultSet.getString(REMARK_COL_NAME)

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
        String sqlQuery = "SELECT " + this.ID_COL_NAME + " FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(this.ID_COL_NAME) == id) {
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
                                + ID_COL_NAME + " INTEGER, "
                                + FLIGHT_NUMBER_COL_NAME + " VARCHAR(10),"
                                + DEPARTURE_CITY_COL_NAME + " VARCHAR(100), "
                                + ARRIVAL_CITY_COL_NAME + " VARCHAR(100),"
                                + DEPARTURE_DATE_COL_NAME + " DATE,"
                                + DEPARTURE_TIME_COL_NAME + " TIME, "
                                + ARRIVAL_DATE_COL_NAME + " DATE,"
                                + ARRIVAL_TIME_COL_NAME + " TIME, "
                                + REMARK_COL_NAME + " VARCHAR(50)"
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
