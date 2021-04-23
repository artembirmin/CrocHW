package ru.artembirmin.croc.finalhw.data.db;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Провайдер базы данных.
 */
public class DataSourceProvider {

    /**
     * Параметры конфигурации.
     */
    private final Map<String, String> properties = new HashMap<>();
    /**
     * Data source.
     */
    private EmbeddedDataSource dataSource;

    public DataSourceProvider() throws IOException {
        loadProperties();
    }

    /**
     * Загружает параметры конфигурации.
     *
     * @throws IOException ошибка при попытке загрузки параметров
     */
    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(
                    Thread.currentThread()
                          .getContextClassLoader()
                          .getResourceAsStream("app.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Error occurred during loading properties");
            throw e;
        }
    }

    public EmbeddedDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName(properties.get("database.name"));
            String username = properties.get("database.username");
            String password = properties.get("database.password");
            if (username != null && !username.isEmpty()
                    && password != null && !password.isEmpty()) {
                dataSource.setUser(username);
                dataSource.setPassword(password);
            }
            dataSource.setCreateDatabase("create");
        }

        return dataSource;
    }
}