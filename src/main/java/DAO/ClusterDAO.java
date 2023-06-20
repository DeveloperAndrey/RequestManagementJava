package DAO;

import model.Cluster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClusterDAO extends GenericDAO<Cluster> {

    public Cluster add(Cluster cluster) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO cluster (title, description) VALUES (?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setString(1, cluster.getTitle());
            statement.setString(2, cluster.getDescription());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                cluster.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return cluster;
    }
    public Cluster update(Cluster cluster) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE cluster SET title=?, description=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, cluster.getTitle());
            statement.setString(2, cluster.getDescription());
            statement.setInt(3, cluster.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return cluster;
    }

    public Cluster delete(int clusterId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM cluster WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, clusterId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<Cluster> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Cluster> clusterList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM cluster";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cluster cluster = new Cluster();
                cluster.setId(resultSet.getInt("id"));
                cluster.setTitle(resultSet.getString("title"));
                cluster.setDescription(resultSet.getString("description"));
                clusterList.add(cluster);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return clusterList;
    }

    public Cluster getById(int clusterId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM cluster WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, clusterId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Cluster cluster = new Cluster();
                cluster.setId(resultSet.getInt("id"));
                cluster.setTitle(resultSet.getString("title"));
                cluster.setDescription(resultSet.getString("description"));
                return cluster;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }
}
