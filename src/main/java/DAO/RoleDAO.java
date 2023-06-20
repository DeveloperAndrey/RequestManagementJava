package DAO;

import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends GenericDAO<Role> {
    public Role add(Role role) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO role (title, description, basic) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setString(1, role.getTitle());
            statement.setString(2, role.getDescription());
            statement.setBoolean(3, role.isBasic());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                role.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return role;
    }

    public Role update(Role role) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            String sql = "UPDATE role SET title=?, description=?, basic=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, role.getTitle());
            statement.setString(2, role.getDescription());
            statement.setBoolean(3, role.isBasic());
            statement.setInt(4, role.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return role;
    }

    // Метод для удаления пользователя из базы данных
    public Role delete(int roleId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM role WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, roleId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    // Метод для получения списка всех пользователей из базы данных
    public List<Role> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Role> roleList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM role";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setTitle(resultSet.getString("title"));
                role.setDescription(resultSet.getString("description"));
                role.setBasic(resultSet.getBoolean("basic"));
                roleList.add(role);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return roleList;
    }

    public Role getById(int roleId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM role WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, roleId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setTitle(resultSet.getString("title"));
                role.setDescription(resultSet.getString("description"));
                role.setBasic(resultSet.getBoolean("basic"));
                return role;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }
}
