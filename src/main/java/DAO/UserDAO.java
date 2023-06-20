package DAO;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends GenericDAO<User>{
    public User add(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO \"user\" (login, password, username, birthdate) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUsername());
            statement.setDate(4, new java.sql.Date(user.getBirthDate().getTime()));

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return user;
    }

    public User update(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE \"user\" SET login=?, password=?, username=?, birthdate=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUsername());
            statement.setDate(4, new java.sql.Date(user.getBirthDate().getTime()));
            statement.setInt(5, user.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return user;
    }

    public User delete(int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM \"user\" WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<User> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM \"user\"";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setBirthDate(resultSet.getDate("birthDate"));
                user.setRole(new UserRoleDAO().getRoleUserId(resultSet.getInt("id")));
                userList.add(user);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return userList;
    }

    public User getById(int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM \"user\" WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setBirthDate(resultSet.getDate("birthdate"));
                user.setRole(new UserRoleDAO().getRoleUserId(resultSet.getInt("id")));
                return user;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    // Метод для получения пользователя по логину и паролю
    public User getUserByLoginAndPassword(String login, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM \"user\" WHERE login=? AND password=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, login);
            statement.setString(2, password);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setBirthDate(resultSet.getDate("birthdate"));
                user.setRole(new UserRoleDAO().getRoleUserId(resultSet.getInt("id")));
                return user;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }
    public  User getUserByLogin(String login) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user;
        try {
            connection = getConnection();

            statement = connection.prepareStatement("SELECT * FROM \"user\" WHERE login = ?");

            statement.setString(1, login);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setBirthDate(resultSet.getDate("birthdate"));
                user.setRole(new UserRoleDAO().getRoleUserId(resultSet.getInt("id")));
                return user;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }
}
