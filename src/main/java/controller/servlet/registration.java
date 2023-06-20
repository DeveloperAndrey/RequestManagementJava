package controller.servlet;

import DAO.MenuDAO;
import DAO.RoleDAO;
import DAO.UserDAO;
import DAO.UserRoleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Menu;
import model.Role;
import model.User;
import model.UserRole;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/registration") // Указываем URL-адрес для обработки
public class registration extends HttpServlet {
    private final UserDAO UserDAO = new UserDAO();
    private final RoleDAO RoleDAO = new RoleDAO();
    private final UserRoleDAO UserRoleDAO = new UserRoleDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Role> roleList = null;
        try {
            roleList = RoleDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String html = roleList.stream()
                .filter(role -> role.isBasic())
                .map(role -> "<option value='" + role.getId() + "'>" + role.getTitle() + "</option>")
                .collect(Collectors.joining());
        request.setAttribute("role", html);
        request.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String username = req.getParameter("username");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(req.getParameter("birthDate"));
            int role = Integer.parseInt(req.getParameter("role"));

            resp.setContentType("text/html");
//            resp.getWriter().println(login);
//            resp.getWriter().println(password);
//            resp.getWriter().println(username);
//            resp.getWriter().println(new java.sql.Date(birthDate.getTime()));
//            resp.getWriter().println(birthDate);
//            resp.getWriter().println(role);

            if (UserDAO.getUserByLogin(login) != null){
                String cookieValue = "Логин занят";
                String encodedValue = Base64.getEncoder().encodeToString(cookieValue.getBytes(StandardCharsets.UTF_8));
                Cookie cookie = new Cookie("error", encodedValue);
                cookie.setMaxAge(20); // Время жизни куки в секундах
                resp.addCookie(cookie);
                resp.sendRedirect("registration");
                return;
            }

            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setUsername(username);
            user.setBirthDate(birthDate);
            UserRole userRole = new UserRole();
            userRole.setUser(UserDAO.add(user));
            userRole.setRole(new RoleDAO().getById(role));
            UserRoleDAO.add(userRole);

            resp.sendRedirect("login");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Ошибка: Некоректные данные 1</h1>");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Ошибка: Некоректные данные 2</h1>");
        } catch (ParseException e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Ошибка: Некоректные данные 3</h1>");
        } catch (SQLException e) {
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Ошибка: База данных</h1>");
            e.printStackTrace();
        }
    }
}
