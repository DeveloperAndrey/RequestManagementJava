package controller.servlet;

import DAO.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import model.Module;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@WebServlet("/") // Указываем URL-адрес для обработки
public class index extends HttpServlet {
    private final MenuDAO MenuDAO = new MenuDAO();
    private final UserDAO UserDAO = new UserDAO();
    private final RequestDAO RequestDAO = new RequestDAO();
    private final RoleDAO RoleDAO = new RoleDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            String module = request.getParameter("module");
            String status = request.getParameter("status");
            String importance = request.getParameter("importance");

            String html;

//            List<Role> roleList;
//            try {
//                roleList = RoleDAO.getAll();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            html = roleList.stream()
//                    .map(roles -> "<option value='" + roles.getTitle() + "'" + (roles.getTitle().equals(role) ? " selected" : "") + ">" + roles.getTitle() + "</option>")
//                    .collect(Collectors.joining());
//            request.setAttribute("role", html);

            List<Module> moduleList;
            try {
                moduleList = new ModuleDAO().getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            html = moduleList.stream()
                    .map(modules -> "<option value='" + modules.getTitle() + "'" + (modules.getTitle().equals(module) ? " selected" : "") + ">" + modules.getTitle() + "</option>")
                    .collect(Collectors.joining());
            request.setAttribute("modules", html);
            List<Status> statusList;
            try {
                statusList = new StatusDAO().getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            html = statusList.stream()
                    .map(statuss -> "<option value='" + statuss.getTitle() + "'" + (statuss.getTitle().equals(status) ? " selected" : "") + ">" + statuss.getTitle() + "</option>")
                    .collect(Collectors.joining());
            request.setAttribute("statuss", html);
            List<Importance> importanceList;
            try {
                importanceList = new ImportanceDAO().getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            html = importanceList.stream()
                    .map(importances -> "<option value='" + importances.getTitle() + "'" + (importances.getTitle().equals(importance) ? " selected" : "") + ">" + importances.getTitle() + "</option>")
                    .collect(Collectors.joining());
            request.setAttribute("importances", html);


            List<Menu> menuList;
            try {
                menuList = MenuDAO.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            html = menuList.stream()
                    .filter(menu -> menu.getRole().isEmpty() || menu.getRole().stream().map(Role::getId).anyMatch(id -> user.getRole().stream().map(Role::getId).anyMatch(id::equals)))
                    .map(menu -> "<li class=\"nav-item\"><a class=\"nav-link active\" href='" + menu.getLink()+ "'>" + menu.getTitle() + "</a></li>")
                    .collect(Collectors.joining());
            request.setAttribute("menu", html);

//        List<User> userList;
//        try {
//            userList = UserDAO.getAll();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//            String search = request.getParameter("search");
//            String recordsPerPageParam = request.getParameter("recordsPerPage");
//            int recordsPerPage = recordsPerPageParam != null ? Integer.parseInt(recordsPerPageParam) : 10;
//            String currentPageParam = request.getParameter("currentPage");
//            int currentPage = currentPageParam != null ? Integer.parseInt(currentPageParam) : 1;
//
//            long count = userList.stream()
//                    .filter(users -> role == null || role.isEmpty() || users.getRole().stream().map(Role::getTitle).anyMatch(title -> title.equals(role)))
//                    .filter(users -> search == null || search.isEmpty() || users.getLogin().contains(search) || users.getUsername().contains(search) || users.getBirthDate().toString().contains(search) || users.getRole().stream().map(Role::getTitle).anyMatch(title -> title.contains(search)))
//                    .count();
//
//            int totalPages = (int) Math.ceil((double) count / recordsPerPage);
//            int startIndex = (currentPage - 1) * recordsPerPage;
//
//            html = userList.stream()
//                    .filter(users -> role == null || role.isEmpty() || users.getRole().stream().map(Role::getTitle).anyMatch(title -> title.equals(role)))
//                    .filter(users -> search == null || search.isEmpty() || users.getLogin().contains(search) || users.getUsername().contains(search) || users.getBirthDate().toString().contains(search) || users.getRole().stream().map(Role::getTitle).anyMatch(title -> title.contains(search)))
//                    .skip(startIndex)
//                    .limit(recordsPerPage)
//                    .map(users -> "<tr data-id='" + users.getId() + "'><td>" + users.getLogin() + "</td><td>" + users.getUsername() + "</td><td>" + users.getBirthDate() + "</td><td>" + users.getRole().stream().map(Role::getTitle).collect(Collectors.joining(", ")) + "</td></tr>")
//                    .collect(Collectors.joining());
//            request.setAttribute("users", html);
//
//            request.setAttribute("totalPages", totalPages);
//            request.setAttribute("currentPage", currentPage);

            List<Request> requestList;
            try {
                requestList = RequestDAO.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String search = request.getParameter("search");
            String recordsPerPageParam = request.getParameter("recordsPerPage");
            int recordsPerPage = recordsPerPageParam != null ? Integer.parseInt(recordsPerPageParam) : 10;
            String currentPageParam = request.getParameter("currentPage");
            int currentPage = currentPageParam != null ? Integer.parseInt(currentPageParam) : 1;

            long count = requestList.stream()
                    .filter(reque -> module == null || module.isEmpty() || reque.getModule().getTitle().contains(module))
                    .filter(reque -> status == null || status.isEmpty() || reque.getStatus().getTitle().contains(status))
                    .filter(reque -> importance == null || importance.isEmpty() || reque.getImportance().getTitle().contains(importance))
                    .filter(reque -> search == null || search.isEmpty() || reque.getModule().getTitle().contains(search) || reque.getStatus().getTitle().contains(search) || reque.getImportance().getTitle().contains(search) || reque.getResponsible().getUsername().contains(search))
                    .count();

            int totalPages = (int) Math.ceil((double) count / recordsPerPage);
            int startIndex = (currentPage - 1) * recordsPerPage;

            html = requestList.stream()
                    .filter(reque -> module == null || module.isEmpty() || reque.getModule().getTitle().contains(module))
                    .filter(reque -> status == null || status.isEmpty() || reque.getStatus().getTitle().contains(status))
                    .filter(reque -> importance == null || importance.isEmpty() || reque.getImportance().getTitle().contains(importance))
                    .filter(reque -> search == null || search.isEmpty() || reque.getModule().getTitle().contains(search) || reque.getStatus().getTitle().contains(search) || reque.getImportance().getTitle().contains(search) || reque.getResponsible().getUsername().contains(search))
                    .skip(startIndex)
                    .limit(recordsPerPage)
                    .map(reque -> "<tr data-id='" + reque.getId() + "'><td>" + reque.getModule().getTitle() + "</td><td>" + reque.getStatus().getTitle() + "</td><td>" + reque.getImportance().getTitle() + "</td><td>" + reque.getResponsible().getUsername() + "</td></tr>")
                    .collect(Collectors.joining());
            request.setAttribute("request", html);

            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);

            // Передача данных на страницу login.jsp в папке "WEB-INF/view"
        request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        }

    }
}
