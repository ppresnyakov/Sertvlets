<%@page import = "ru.appline.logic.Model"%>
<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 02.11.2020
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Домашняя страница по работе с пользователеями</h1>
  Введите ID пользователя (0 - вывести всех пользователей)
  <br>
   Доступно:<%
   Model model = Model.getInstance();
  out.print(model.getFromList().size());
  %>
  <form method="get" action="get">
    <label> ID:
    <input type="text" name = "id"><br/>
    </label>
    <button type="submit">Поиск</button>
  </form>
  <a href="addUser.html">Создать нового пользователя </a>
  </body>
</html>
