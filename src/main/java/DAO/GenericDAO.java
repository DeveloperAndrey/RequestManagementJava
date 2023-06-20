package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class  GenericDAO<T> {
    private static DBConnection dbConnection;
    public GenericDAO() {
        dbConnection = new DBConnection();
    }

    public abstract T add(T t) throws SQLException;
    public abstract T update(T t) throws SQLException;
    public abstract T delete(int id) throws SQLException;
    public abstract List<T> getAll() throws SQLException;
    public abstract T getById(int id) throws SQLException;

    protected Connection getConnection() throws SQLException {
        Connection connection = dbConnection.getConnection();
        if (connection == null) {
            throw new SQLException("Не удалось установить соединение с базой данных.");
        }
        return connection;
    }

    protected void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        dbConnection.closeResources(connection, statement, resultSet);
    }
}
