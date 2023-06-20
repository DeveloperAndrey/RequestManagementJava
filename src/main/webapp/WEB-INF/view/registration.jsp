<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 25.04.2023
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Cookie[] cookies = request.getCookies();
    String errorMessage = null;
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("error".equals(cookie.getName())) {
                String encodedValue = cookie.getValue();
                byte[] decodedBytes = Base64.getDecoder().decode(encodedValue);
                errorMessage = new String(decodedBytes, StandardCharsets.UTF_8);
                break;
            }
        }
    }
%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <title>Управление заявками - Регистарция</title>
        <style><%@include file="../css/template.css"%>></style>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
        <style><%@include file="../js/template.js"%>></style>
    </head>
    <body class="h-100 container">
        <header></header>
        <div id="main" class="d-flex h-100 justify-content-center flex-column">
            <h1 class="text-center my-4">Управление заявками</h1>
            <form class="w-100 p-4 border" action="<%= request.getContextPath() %>/registration" method="post">
                <a href="<%= request.getContextPath() %>/login" class="btn border px-5 py-3">Авторизация</a>
                <a href="<%= request.getContextPath() %>/registration" class="btn border btn-primary px-5 py-3">Регистрация</a>
                <div class="my-3">
                    <label for="login" class="form-label">Логин</label>
                    <input type="text" class="form-control py-2" id="login" value="" name="login" required>
                    <div class="invalid-feedback">

                    </div>
                </div>
                <div class="my-3">
                    <label for="password" class="form-label">Пароль</label>
                    <input type="password" class="form-control py-2" id="password" value="" name="password" required>
                    <div class="invalid-feedback">

                    </div>
                </div>
                <div class="my-3">
                    <label for="name" class="form-label">Имя</label>
                    <input type="text" class="form-control py-2" id="name" value="" name="username" required>
                    <div class="invalid-feedback">

                    </div>
                </div>
                <div class="my-3">
                    <label for="dob" class="form-label">Дата рождения</label>
                    <input type="date" class="form-control py-2" id="dob" value="" name="birthDate" required>
                    <div class="invalid-feedback">

                    </div>
                </div>
                <div class="my-3">
                    <label for="role" class="form-label">Роль</label>
                    <select class="form-select py-2" id="role" name="role" required>
                        <%= request.getAttribute("role") %>
                    </select>
                    <div class="invalid-feedback">

                    </div>
                </div>
                <% if (errorMessage != null) { %>
                <p><%= errorMessage %></p>
                <% } %>
                <button class="btn btn-primary px-5 py-3" type="submit">Зарегистрироваться</button>
            </form>
        </div>
        <footer></footer>
    </body>
</html>


