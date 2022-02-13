<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        TABLE {
            border: 1px solid black; /* Рамка вокруг таблицы */
        }
        TD, TH {
            padding: 3px; /* Поля вокруг содержимого ячеек */
            border: 1px solid black;
        }
        TH {
            text-align: left; /* Выравнивание по левому краю */
            background: white; /* Цвет фона */
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<td><a href="meals?action=create&mealId=<c:out value="${meal.id}"/>">Create</a></td>
<table>
    <c:forEach items="${mealsTo}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr style="color:${meal.excess ? 'red' : 'green'}";>
            <td>${meal.id}</td>
            <td>${FORMATTER.format(meal.dateTime)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.excess}</td>
            <td><a href="meals?action=update&id=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Remove</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
