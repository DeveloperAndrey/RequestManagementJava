package DAO;

import model.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO extends GenericDAO<Menu> {
    public Menu add(Menu menu) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO menu (title, link) VALUES (?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setString(1, menu.getTitle());
            statement.setString(2, menu.getLink());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                menu.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, null);
        }
        return menu;
    }

    public Menu update(Menu menu) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE menu SET title=?, link=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, menu.getTitle());
            statement.setString(2, menu.getLink());
            statement.setInt(3, menu.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return menu;
    }

    public Menu delete(int menuId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM menu WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, menuId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<Menu> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Menu> menuList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM menu";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Menu menu = new Menu();
                menu.setId(resultSet.getInt("id"));
                menu.setTitle(resultSet.getString("title"));
                menu.setLink(resultSet.getString("link"));
                menu.setRole(new MenuRoleDAO().getRoleMenuId(resultSet.getInt("id")));
                menuList.add(menu);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return menuList;
    }

    public Menu getById(int menuId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM menu WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, menuId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Menu menu = new Menu();
                menu.setId(resultSet.getInt("id"));
                menu.setTitle(resultSet.getString("title"));
                menu.setLink(resultSet.getString("link"));
                menu.setRole(new MenuRoleDAO().getRoleMenuId(resultSet.getInt("id")));
                return menu;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

}
