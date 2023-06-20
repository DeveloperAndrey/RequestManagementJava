package controller.servlet;

import DAO.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import model.Module;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/createRequest")
public class addRequest extends HttpServlet {
    private final MenuDAO MenuDAO = new MenuDAO();
    private final ModuleDAO ModuleDAO = new ModuleDAO();
    private final ImportanceDAO ImportanceDAO = new ImportanceDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            String html;

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
            req.setAttribute("menu", html);

            List<Importance> importanceList;
            try {
                importanceList = ImportanceDAO.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            html = importanceList.stream()
                    .map(importance -> "<option value='" + importance.getId() + "'>" + importance.getTitle() + "</option>")
                    .collect(Collectors.joining());
            req.setAttribute("importance", html);

            List<Module> moduleList;
            try {
                moduleList = ModuleDAO.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Map<String, List<Module>> clusters = new LinkedHashMap<>();
            for (Module module : moduleList) {
                String clusterTitle = module.getCluster().getTitle();
                if (!clusters.containsKey(clusterTitle)) {
                    clusters.put(clusterTitle, new ArrayList<>());
                }
                clusters.get(clusterTitle).add(module);
            }

            StringBuilder block = new StringBuilder();
            String contextPath = req.getContextPath();
            for (Map.Entry<String, List<Module>> entry : clusters.entrySet()) {
                String clusterTitle = entry.getKey();
                List<Module> modules = entry.getValue();
                block.append("<h2>").append(clusterTitle).append("</h2>");
                block.append("<hr>");
                block.append("<div class=\"row\">");
                for (Module module : modules) {
                    block.append("<form class=\"col-xl-4 my-3\" action=\"").append(contextPath).append("/createRequest\" method=\"get\">").append("<div class=\"border px-5 py-4\">").append("<h3>").append(module.getTitle()).append("</h3>")
                            .append("<input type=\"hidden\" name=\"moduleId\" value=\"").append(module.getId()).append("\">")
                            .append("<button href=\"#\" class=\"btn btn-outline-primary  w-100 mt-3\" type=\"submit\">Выбрать</button>").append("</div>").append("</form>");
                }
                block.append("</div>");
            }
            req.setAttribute("module", block.toString());

            req.getRequestDispatcher("/WEB-INF/view/createRequest.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            try {
                int module = Integer.parseInt(req.getParameter("module"));
                int importance = Integer.parseInt(req.getParameter("importance"));
                String commentText = req.getParameter("comment");

                Request request = new Request();
                request.setModule(ModuleDAO.getById(module));
                request.setStatus(new StatusDAO().getById(1));
                request.setDate_create(new java.sql.Date(System.currentTimeMillis()));
                request.setDate_update(new java.sql.Date(System.currentTimeMillis()));
                request.setUser(user);
                request.setResponsible(new UserDAO().getById(23));
                request.setObserver(new UserDAO().getById(23));
                request.setImportance(ImportanceDAO.getById(importance));
                System.out.println(request);
                Comment comment = new Comment();
                comment.setUser(user);
                comment.setDate(new java.sql.Date(System.currentTimeMillis()));
                comment.setText(commentText);
                comment.setRequest(new RequestDAO().add(request));
                new CommentDAO().add(comment);

                resp.sendRedirect("index");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                resp.setContentType("text/html");
                resp.getWriter().println("<h1>Ошибка: Некоректные данные 1</h1>");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                resp.setContentType("text/html");
                resp.getWriter().println("<h1>Ошибка: Некоректные данные 2</h1>");
            } catch (SQLException e) {
                resp.setContentType("text/html");
                resp.getWriter().println("<h1>Ошибка: База данных</h1>");
                e.printStackTrace();
            }
        }
    }
}
