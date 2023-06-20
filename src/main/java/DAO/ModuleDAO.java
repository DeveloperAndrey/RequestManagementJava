package DAO;

import model.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleDAO extends GenericDAO<Module> {

    public Module add(Module module) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO module (title, description, id_cluster) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setString(1, module.getTitle());
            statement.setString(2, module.getDescription());
            statement.setInt(3, module.getCluster().getId());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                module.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return module;
    }
    public Module update(Module module) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE module SET title=?, description=?, id_cluster=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, module.getTitle());
            statement.setString(2, module.getDescription());
            statement.setInt(3, module.getCluster().getId());
            statement.setInt(4, module.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return module;
    }

    public Module delete(int moduleId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM module WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, moduleId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<Module> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Module> moduleList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM module";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Module module = new Module();
                module.setId(resultSet.getInt("id"));
                module.setTitle(resultSet.getString("title"));
                module.setDescription(resultSet.getString("description"));
                module.setCluster(new ClusterDAO().getById(resultSet.getInt("id_cluster")));
                moduleList.add(module);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return moduleList;
    }

    public Module getById(int moduleId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM module WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, moduleId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Module module = new Module();
                module.setId(resultSet.getInt("id"));
                module.setTitle(resultSet.getString("title"));
                module.setDescription(resultSet.getString("description"));
                module.setCluster(new ClusterDAO().getById(resultSet.getInt("id_cluster")));
                return module;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }
}

