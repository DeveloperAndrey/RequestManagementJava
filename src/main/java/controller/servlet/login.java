package controller.servlet;

import DAO.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;

@WebServlet("/login")
public class login extends HttpServlet {

    private final UserDAO UserDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        resp.setContentType("text/html");

        resp.getWriter().println(password);
        resp.getWriter().println(login);

        try {
            User user =  UserDAO.getUserByLoginAndPassword(login,password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                user = (User) session.getAttribute("user");
                resp.getWriter().println(user);
                resp.sendRedirect("index");
            } else {
                String cookieValue = "Не верный логин или пароль";
                String encodedValue = Base64.getEncoder().encodeToString(cookieValue.getBytes(StandardCharsets.UTF_8));
                Cookie cookie = new Cookie("error", encodedValue);
                cookie.setMaxAge(20); // Время жизни куки в секундах
                resp.addCookie(cookie);

                resp.sendRedirect("login");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
