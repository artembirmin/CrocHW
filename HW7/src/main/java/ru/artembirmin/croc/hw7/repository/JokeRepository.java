package ru.artembirmin.croc.hw7.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.artembirmin.croc.hw7.model.Joke;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с базой данных шуток.
 */
public class JokeRepository implements BaseRepository<Joke> {

    /**
     * Имя таблицы.
     */
    private static final String TABLE_NAME = "joke";

    /**
     * Максимальный идентификатор.
     */
    private static int maxId;

    /**
     * Ресурс.
     */
    private final EmbeddedDataSource dataSource;

    /**
     * Название столбца с идентификатором.
     */
    private final String idColName = "id";

    /**
     * Название столбца с текстом шутки.
     */
    private final String jokeTextColName = "jokeText";

    /**
     * Название столбца с количеством слов.
     */
    private final String wordsCountColName = "wordsCount";

    /**
     * Название столбца с характеристикой забавности шутки.
     */
    private final String isFunnyColName = "isFunny";

    /**
     * Название столбца с датой создания.
     */
    private final String creationDateColName = "creationDate";

    /**
     * Название столбца с ресурсом.
     */
    private final String sourceColName = "source";

    /**
     * @param dataSource ресурс
     * @throws SQLException если во время нахождения maxId произошла ошибка
     */
    public JokeRepository(EmbeddedDataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        initTable();
        maxId = findMaxId();
    }

    @Override
    public Joke findById(int id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + this.idColName + " = " + id;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Joke(
                        resultSet.getInt(this.idColName),
                        resultSet.getString(jokeTextColName),
                        resultSet.getInt(wordsCountColName),
                        resultSet.getBoolean(isFunnyColName),
                        resultSet.getDate(creationDateColName).toLocalDate(),
                        resultSet.getString(sourceColName)
                );
            }
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Joke> findAll() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            List<Joke> taskList = new ArrayList<>();
            while (resultSet.next()) {
                //FIXME
                taskList.add(
                        new Joke(
                                resultSet.getInt(idColName),
                                resultSet.getString(jokeTextColName),
                                resultSet.getInt(wordsCountColName),
                                resultSet.getBoolean(isFunnyColName),
                                resultSet.getDate(creationDateColName).toLocalDate(),
                                resultSet.getString(sourceColName)
                        ));
            }
            return taskList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Joke save(Joke joke) {
        if (isTableContainsId(joke.getId())) {
            String sqlQuery = "UPDATE " + TABLE_NAME
                    + " SET " + jokeTextColName + " = '" + joke.getText()
                    + "', " + wordsCountColName + " = " + joke.getWordsCount()
                    + ", " + isFunnyColName + " = " + joke.isFunny()
                    + ", " + creationDateColName + " = '" + Date.valueOf(joke.getCreationDate())
                    + "', " + sourceColName + " = '" + joke.getSource()
                    + "' WHERE " + idColName + " = " + joke.getId();
            executeSqlCommand(sqlQuery);
        } else {
            createNew(joke);
        }
        return joke;
    }

    @Override
    public List<Joke> saveAll(List<Joke> jokes) {
        for (Joke joke : jokes) {
            save(joke);
        }
        return jokes;
    }

    @Override
    public void delete(Joke joke) {
        delete(joke.getId());
    }

    @Override
    public void delete(int id) {
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE " + this.idColName + " = " + id;
        executeSqlCommand(sqlQuery);
    }

    @Override
    public void deleteAll() {
        maxId = 0;
        String sqlQuery = "DELETE FROM " + TABLE_NAME;
        executeSqlCommand(sqlQuery);
    }

    @Override
    public Joke createNew(Joke joke) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, ++maxId);
            statement.setString(2, joke.getText());
            statement.setInt(3, joke.getWordsCount());
            statement.setBoolean(4, joke.isFunny());
            statement.setDate(5, Date.valueOf(joke.getCreationDate()));
            statement.setString(6, joke.getSource());
            statement.execute();
            joke.setId(maxId);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return joke;
    }

    @Override
    public List<Joke> createNewAll(List<Joke> jokes) {
        for (Joke joke : jokes) {
            createNew(joke);
        }
        return jokes;
    }

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
     * <p>
     * Оказывается, SQL диалект, используемый JavaDB, не умеет в "IF NOT EXISTS" :(
     * Поэтому пришлось найти небольшой workaround, который предварительно проверяет существование таблицы в базе.
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
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + idColName + " INTEGER, "
                                + jokeTextColName + " VARCHAR(4096),"
                                + wordsCountColName + " INTEGER, "
                                + isFunnyColName + " BOOLEAN,"
                                + creationDateColName + " DATE,"
                                + sourceColName + " VARCHAR(256)"
                                + ")");
                System.out.println("Table was successfully initialized");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during table initializing: " + e.getMessage());
        } finally {
            System.out.println("===========================================");
        }
    }

    /**
     * Возвращает максимальный idColName в базе.
     *
     * @return максимальный idColName в базе
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
}
