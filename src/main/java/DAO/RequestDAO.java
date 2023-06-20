package DAO;

import model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO extends GenericDAO<Request> {

    public Request add(Request request) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = getConnection();

            String sql = "INSERT INTO request (id_module, id_status, date_create, date_update, id_user, id_user_responsible, id_user_observer, id_importance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, request.getModule().getId());
            statement.setInt(2, request.getStatus().getId());
            statement.setDate(3, (Date) request.getDate_create());
            statement.setDate(4, (Date) request.getDate_update());
            statement.setInt(5, request.getUser().getId());
            statement.setInt(6, request.getResponsible().getId());
            statement.setInt(7, request.getObserver().getId());
            statement.setInt(8, request.getImportance().getId());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                request.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return request;
    }
    public Request update(Request request) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE request SET id_module=?, id_status=?, date_create=?, date_update=?, id_user=?, id_user_responsible=?, id_user_observer=?, id_importance=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, request.getModule().getId());
            statement.setInt(2, request.getStatus().getId());
            statement.setDate(3, (Date) request.getDate_create());
            statement.setDate(4, (Date) request.getDate_update());
            statement.setInt(5, request.getUser().getId());
            statement.setInt(6, request.getResponsible().getId());
            statement.setInt(7, request.getObserver().getId());
            statement.setInt(8, request.getImportance().getId());
            statement.setInt(9, request.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return request;
    }

    public Request delete(int requestId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM request WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, requestId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<Request> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Request> requestList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM request";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Request request = new Request();
                request.setId(resultSet.getInt("id"));
                request.setModule(new ModuleDAO().getById(resultSet.getInt("id_module")));
                request.setStatus(new StatusDAO().getById(resultSet.getInt("id_status")));
                request.setDate_create(resultSet.getDate("date_create"));
                request.setDate_update(resultSet.getDate("date_update"));
                request.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                request.setResponsible(new UserDAO().getById(resultSet.getInt("id_user_responsible")));
                request.setObserver(new UserDAO().getById(resultSet.getInt("id_user_observer")));
                request.setImportance(new ImportanceDAO().getById(resultSet.getInt("id_importance")));
                request.setComment(new CommentDAO().getByRequest(resultSet.getInt("id")));
                requestList.add(request);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return requestList;
    }

    public Request getById(int requestId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM request WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, requestId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Request request = new Request();
                request.setId(resultSet.getInt("id"));
                request.setModule(new ModuleDAO().getById(resultSet.getInt("id_module")));
                request.setStatus(new StatusDAO().getById(resultSet.getInt("id_status")));
                request.setDate_create(resultSet.getDate("date_create"));
                request.setDate_update(resultSet.getDate("date_update"));
                request.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                request.setResponsible(new UserDAO().getById(resultSet.getInt("id_user_responsible")));
                request.setObserver(new UserDAO().getById(resultSet.getInt("id_user_observer")));
                request.setImportance(new ImportanceDAO().getById(resultSet.getInt("id_importance")));
                request.setComment(new CommentDAO().getByRequest(resultSet.getInt("id")));
                return request;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }
}

