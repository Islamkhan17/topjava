<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.02.2022
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create or update meal</title>
    <style type="text/css">
        FORM {
            display: table;
            width: 300px; /* Ширина таблицы */
            padding: 1em;
            border: 1px solid #CCC;
            border-radius: 1em;
        }

        label {
            display: inline-block;
            text-align: left;
            width: 100px;
        }

        input {
            width: 150px;
            box-sizing: border-box;
            /*display: table-cell;*/
            padding: 3px; /* Поля вокруг содержимого ячеек */
            border: 1px solid black;
            text-align: left; /* Выравнивание по левому краю */
            background: white; /* Цвет фона */
            margin-top: 10px;
        }
        input text value{
            visibility: ${hidden} ;
        }

    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>
    <c:choose>
        <c:when test="${meal eq null}">
            <c:set var="hidden" value="hidden"/>
        </c:when>
        <c:otherwise>
            <c:set var="hidden" value="visible"/>
        </c:otherwise>
    </c:choose>
<form method="POST" action='meals' name="formCUMeal">
    <label for="mealId">Meal id</label>
    <input type="text" readonly="readonly" name="mealId"
           value="<c:out value="${meal.id}" />"/> <br/>
    <label for="calories">Calories</label>
    <input type="text" name="calories"
           value="<c:out value="${meal.calories}" />"/> <br/>
    <label for="description">Description</label>
    <input type="text" name="description"
           value="<c:out value="${meal.description}" />"/> <br/>
    <label for="date">Description</label><input
        type="datetime-local" name="date"
        value="<c:out value="${meal.dateTime}" />"/> <br/>
    <input
            class = "submit" type="submit" value="Submit"/>
</form>
</body>
</html>
