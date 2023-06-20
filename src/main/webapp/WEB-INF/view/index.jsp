<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 25.04.2023
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <title>Управление заявками - Главная</title>
        <style><%@include file="../css/template.css"%>></style>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
        <style><%@include file="../js/template.js"%>></style>
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
            <div class="d-flex flex-column my-5">
                <h1 class="text-center my-4">Подайте заявку сейчас</h1>
                <a href="#" class="btn btn-primary py-4 w-50 mx-auto my-4">Новая заявка</a>
            </div>
            <div class="d-flex flex-column my-5">
                <h2 class="text-center my-4">Или посмотрете Ваши заявки</h2>
<%--                <form class="d-flex align-items-end" action="<%= request.getContextPath() %>/" method="get">--%>
<%--                    <div class="my-3">--%>
<%--                        <label for="search" class="form-label">Поиск</label>--%>
<%--                        <input type="text" class="form-control py-2" id="search" name="search" value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">--%>
<%--                        <div class="invalid-feedback">--%>

<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="my-3 mx-2">--%>
<%--                        <label for="role" class="form-label">Роль</label>--%>
<%--                        <select class="form-select py-2" id="role" name="role" >--%>
<%--                            <option value="" <%= request.getParameter("role") == null || request.getParameter("role").isEmpty() ? "selected" : "" %>>Все</option>--%>
<%--                            <%= request.getAttribute("role") %>--%>
<%--                        </select>--%>
<%--                        <div class="invalid-feedback">--%>

<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="my-3">--%>
<%--                        <label for="quantity" class="form-label">Кол. данных</label>--%>
<%--                        <input type="number" class="form-control py-2" id="quantity" name="recordsPerPage" value="<%= request.getParameter("recordsPerPage") != null ? request.getParameter("recordsPerPage") : 10 %>">--%>
<%--                        <div class="invalid-feedback">--%>

<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <button class="btn btn-primary py-2 my-3 mx-2" type="submit">Применить фильтр</button>--%>
<%--                </form>--%>
                <form class="d-flex align-items-end" action="<%= request.getContextPath() %>/" method="get">
                    <div class="my-3">
                        <label for="search" class="form-label">Поиск</label>
                        <input type="text" class="form-control py-2" id="search" name="search" value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
                        <div class="invalid-feedback">

                        </div>
                    </div>
                    <div class="my-3 mx-2">
                        <label for="module" class="form-label">Роль</label>
                        <select class="form-select py-2" id="module" name="module" >
                            <option value="" <%= request.getParameter("module") == null || request.getParameter("module").isEmpty() ? "selected" : "" %>>Все</option>
                            <%= request.getAttribute("modules") %>
                        </select>
                        <div class="invalid-feedback">

                        </div>
                    </div>
                    <div class="my-3 mx-2">
                        <label for="status" class="form-label">Роль</label>
                        <select class="form-select py-2" id="status" name="status" >
                            <option value="" <%= request.getParameter("status") == null || request.getParameter("status").isEmpty() ? "selected" : "" %>>Все</option>
                            <%= request.getAttribute("statuss") %>
                        </select>
                        <div class="invalid-feedback">

                        </div>
                    </div>
                    <div class="my-3 mx-2">
                        <label for="importance" class="form-label">Роль</label>
                        <select class="form-select py-2" id="importance" name="importance" >
                            <option value="" <%= request.getParameter("importance") == null || request.getParameter("importance").isEmpty() ? "selected" : "" %>>Все</option>
                            <%= request.getAttribute("importances") %>
                        </select>
                        <div class="invalid-feedback">

                        </div>
                    </div>
                    <div class="my-3">
                        <label for="quantity" class="form-label">Кол. данных</label>
                        <input type="number" class="form-control py-2" id="quantity" name="recordsPerPage" value="<%= request.getParameter("recordsPerPage") != null ? request.getParameter("recordsPerPage") : 10 %>">
                        <div class="invalid-feedback">

                        </div>
                    </div>
                    <button class="btn btn-primary py-2 my-3 mx-2" type="submit">Применить фильтр</button>
                </form>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Handle</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%= request.getAttribute("request") %>
                    </tbody>
                </table>

                <% if ((int) request.getAttribute("totalPages") > 1) { %>
                <ul class="pagination">
                    <% for (int i = 1; i <= (int) request.getAttribute("totalPages"); i++) { %>
                    <li class="page-item <%= (int) request.getAttribute("currentPage") == i ? "active" : "" %>">
                        <a class="page-link" href="?currentPage=<%= i %>&search=<%= request.getParameter("search") %>&recordsPerPage=<%= request.getParameter("recordsPerPage") %>"><%= i %></a>
                    </li>
                    <% } %>
                </ul>
                <% } %>
            </div>
        </div>

        <div class="modal" id="staticBackdrop">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-body d-flex flex-column">
                        dasdasd
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <footer></footer>
        <% } %>
    </body>
</html>

<%--<%= request.getAttribute("menu") %>--%>
<%--<%= request.getAttribute("pagination") %>--%>
