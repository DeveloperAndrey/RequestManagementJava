package DAO;

import model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO extends GenericDAO<Status> {

    public Status add(Status status) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO status (title, description) VALUES (?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setString(1, status.getTitle());
            statement.setString(2, status.getDescription());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                status.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return status;
    }
    public Status update(Status status) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE status SET title=?, description=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, status.getTitle());
            statement.setString(2, status.getDescription());
            statement.setInt(3, status.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return status;
    }

    public Status delete(int statusId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM status WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, statusId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<Status> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Status> statusList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM status";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Status status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setTitle(resultSet.getString("title"));
                status.setDescription(resultSet.getString("description"));
                statusList.add(status);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return statusList;
    }

    public Status getById(int statusId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM status WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, statusId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Status status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setTitle(resultSet.getString("title"));
                status.setDescription(resultSet.getString("description"));
                return status;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }
}

