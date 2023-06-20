package DAO;

import model.Comment;
import model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends GenericDAO<Comment> {

    public Comment add(Comment comment) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "INSERT INTO comment (id_user, date, text, id_request) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, comment.getUser().getId());
            statement.setDate(2, (Date) comment.getDate());
            statement.setString(3, comment.getText());
            statement.setInt(4, comment.getRequest().getId());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            closeResources(connection, statement, null);
        }
        return comment;
    }
    public Comment update(Comment comment) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "UPDATE comment SET id_user=?, date=?, text=?, id_request=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, comment.getUser().getId());
            statement.setDate(2, (Date) comment.getDate());
            statement.setString(3, comment.getText());
            statement.setInt(4, comment.getRequest().getId());
            statement.setInt(5, comment.getId());

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return comment;
    }

    public Comment delete(int commentId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();

            String sql = "DELETE FROM comment WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, commentId);

            statement.executeUpdate();
        } finally {
            closeResources(connection, statement, null);
        }
        return null;
    }

    public List<Comment> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Comment> commentList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM comment";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                comment.setDate(resultSet.getDate("date"));
                comment.setText(resultSet.getString("text"));
                comment.setRequest(new RequestDAO().getById(resultSet.getInt("id_request")));
                commentList.add(comment);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return commentList;
    }

    public Comment getById(int commentId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();

            String sql = "SELECT * FROM comment WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, commentId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                comment.setDate(resultSet.getDate("date"));
                comment.setText(resultSet.getString("text"));
                comment.setRequest(new RequestDAO().getById(resultSet.getInt("id_request")));
                return comment;
            } else {
                return null;
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
    }

    public List<Comment> getByRequest(int requestId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Comment> commentList = new ArrayList<>();
        try {
            connection = getConnection();

            String sql = "SELECT * FROM comment WHERE id_request=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, requestId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                comment.setDate(resultSet.getDate("date"));
                comment.setText(resultSet.getString("text"));
                commentList.add(comment);
            }
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return commentList;
    }
}

