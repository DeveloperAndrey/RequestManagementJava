package DAO;

import java.sql.*;
import java.util.Properties;


public class DBConnection {

    private final String url;
    private final Properties props = new Properties();

    public DBConnection() {
        url = "jdbc:postgresql://localhost:5432/courseProject";
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("client_encoding", "UTF8");
    }

    // Метод для подключения к БД
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, props);
    }

    // Метод для закрытия ресурсов
    public void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

