package DAO;

import model.UserRole;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAO extends GenericDAO<UserRole> {

    public UserRole add(UserRole userRole) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO \"userRole\" (id_user, id_role) VALUES (?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, userRole.getUser().getId());
            statement.setInt(2, userRole.getRole().getId());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userRole.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return userRole;
    }
    public UserRole update(UserRole userRole) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE \"userRole\" SET id_user=?, id_role=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userRole.getUser().getId());
            statement.setInt(2, userRole.getRole().getId());
            statement.setInt(3, userRole.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return userRole;
    }

    public UserRole delete(int userRoleId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM \"userRole\" WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userRoleId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<UserRole> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserRole> userRoleList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM \"userRole\"";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserRole userRole = new UserRole();
                userRole.setId(resultSet.getInt("id"));
                userRole.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                userRole.setRole(new RoleDAO().getById(resultSet.getInt("id_role")));
                userRoleList.add(userRole);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return userRoleList;
    }

    public UserRole getById(int userRoleId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM \"userRole\" WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userRoleId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserRole userRole = new UserRole();
                userRole.setId(resultSet.getInt("id"));
                userRole.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                userRole.setRole(new RoleDAO().getById(resultSet.getInt("id_role")));
                return userRole;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    public List<Role> getRoleUserId(int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Role> roleList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT id_role FROM \"userRole\" WHERE id_user = ?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roleList.add(new RoleDAO().getById(resultSet.getInt("id_role")));
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return roleList;
    }
}