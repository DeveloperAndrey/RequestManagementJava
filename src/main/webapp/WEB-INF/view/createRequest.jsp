<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11.06.2023
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.User" %>
<%@ page import="DAO.ModuleDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="h-100">
<head>
    <head>
        <title>Управление заявками - Новая заявка</title>
        <style><%@include file="../css/template.css"%>></style>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
        <style><%@include file="../js/template.js"%>></style>
    </head>
</head>
<body class="h-100 container p-0">
    <% session = request.getSession(false); %>
    <% if (session != null) { %>
    <% User user = (User) session.getAttribute("user"); %>
    <header>
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Управление заявками</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto">
                        <%= request.getAttribute("menu") %>
                    </ul>
                    <div class="d-flex justify-content-center align-items-center">
                        <p class="m-0 px-3"><%= user.getUsername() %></p>
                        <a href="<%= request.getContextPath() %>/logout" class="btn btn-outline-success" type="submit">Выйти</a>
                    </div>
                </div>
            </div>
        </nav>
    </header>
    <div id="main" class="">
        <h1 class="text-center my-4">Создание заявки</h1>
        <div class="row justify-content-around align-items-center nav nav-pills mb-3" id="pills-tab" role="tablist">
            <li class="nav-item col-xl-6" role="presentation">
                <button class="btn btn-outline-primary w-100 py-3 active" id="pills-home-tab" data-bs-toggle="pill" data-bs-target="#step1" type="button" role="tab" aria-controls="pills-home" aria-selected="true"><%= request.getParameter("moduleId") == null || request.getParameter("moduleId").isEmpty() ? "Выбор проекта/отдела/услуги" : new ModuleDAO().getById(Integer.parseInt(request.getParameter("moduleId"))).getTitle() %></button>
            </li>
            <li class="nav-item col-xl-6" role="presentation">
                <button <%= request.getParameter("moduleId") == null || request.getParameter("moduleId").isEmpty() ? "disabled" : "" %> class="btn btn-outline-primary w-100 py-3 " id="pills-profile-tab" data-bs-toggle="pill" data-bs-target="#step2" type="button" role="tab" aria-controls="pills-profile" aria-selected="false">Формирование заявки</button>
            </li>
        </div>
        <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade show active" id="step1" role="tabpanel">
                <div class="my-5">
                    <%= request.getAttribute("module") %>
                </div>
            </div>
            <form class="tab-pane fade" id="step2" role="tabpanel" action="<%= request.getContextPath() %>/createRequest" method="post">
                <div class="my-5">
                    <p><%= request.getParameter("moduleId") == null || request.getParameter("moduleId").isEmpty() ? "" : new ModuleDAO().getById(Integer.parseInt(request.getParameter("moduleId"))).getCluster().getTitle() %>: <%= request.getParameter("moduleId") == null || request.getParameter("moduleId").isEmpty() ? "" : new ModuleDAO().getById(Integer.parseInt(request.getParameter("moduleId"))).getTitle() %></p>
                    <div>
                        <label for="comment" class="form-label">Опишите суть обращения</label>
                        <textarea class="form-control" id="comment" name="comment" rows="5"></textarea>
                    </div>

                    <div class="my-3">
                        <label for="importance" class="form-label">Приоритет</label>
                        <select class="form-select py-2" id="importance" name="importance" required>
                            <%= request.getAttribute("importance") %>
                        </select>
                        <div class="invalid-feedback">

                        </div>
                    </div>
                    <input type="hidden" name="module" value="<%= request.getParameter("moduleId") == null || request.getParameter("moduleId").isEmpty() ? "" : request.getParameter("moduleId") %>">
                    <button href="#" class="btn btn-primary  w-100 mt-3" type="submit">Подать заявку</button>
                </div>
            </form>
        </div>
    </div>
    <footer></footer>
    <% } %>
</body>
</html>
