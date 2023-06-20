package DAO;

import model.Importance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportanceDAO extends GenericDAO<Importance> {

    public Importance add(Importance importance) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO importance (title, description) VALUES (?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setString(1, importance.getTitle());
            statement.setString(2, importance.getDescription());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                importance.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return importance;
    }
    public Importance update(Importance importance) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE importance SET title=?, description=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, importance.getTitle());
            statement.setString(2, importance.getDescription());
            statement.setInt(3, importance.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return importance;
    }

    public Importance delete(int importanceId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM importance WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, importanceId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<Importance> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Importance> importanceList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM importance";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Importance importance = new Importance();
                importance.setId(resultSet.getInt("id"));
                importance.setTitle(resultSet.getString("title"));
                importance.setDescription(resultSet.getString("description"));
                importanceList.add(importance);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return importanceList;
    }

    public Importance getById(int importanceId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM importance WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, importanceId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Importance importance = new Importance();
                importance.setId(resultSet.getInt("id"));
                importance.setTitle(resultSet.getString("title"));
                importance.setDescription(resultSet.getString("description"));
                return importance;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }
}
