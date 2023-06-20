package DAO;

import model.Role;
import model.MenuRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuRoleDAO extends GenericDAO<MenuRole> {

    public MenuRole add(MenuRole menuRole) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO \"menuRole\" (id_menu, id_role) VALUES (?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, menuRole.getMenu().getId());
            statement.setInt(2, menuRole.getRole().getId());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                menuRole.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return menuRole;
    }
    public MenuRole update(MenuRole menuRole) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE \"menuRole\" SET id_menu=?, id_role=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, menuRole.getMenu().getId());
            statement.setInt(2, menuRole.getRole().getId());
            statement.setInt(3, menuRole.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return menuRole;
    }

    public MenuRole delete(int menuRoleId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM \"menuRole\" WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, menuRoleId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<MenuRole> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<MenuRole> menuRoleList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM \"menuRole\"";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MenuRole menuRole = new MenuRole();
                menuRole.setId(resultSet.getInt("id"));
                menuRole.setMenu(new MenuDAO().getById(resultSet.getInt("id_menu")));
                menuRole.setRole(new RoleDAO().getById(resultSet.getInt("id_role")));
                menuRoleList.add(menuRole);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return menuRoleList;
    }

    public MenuRole getById(int menuRoleId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM \"menuRole\" WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, menuRoleId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                MenuRole menuRole = new MenuRole();
                menuRole.setId(resultSet.getInt("id"));
                menuRole.setMenu(new MenuDAO().getById(resultSet.getInt("id_menu")));
                menuRole.setRole(new RoleDAO().getById(resultSet.getInt("id_role")));
                return menuRole;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    public List<Role> getRoleMenuId(int menuId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Role> roleList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT id_role FROM \"menuRole\" WHERE id_menu = ?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, menuId);

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