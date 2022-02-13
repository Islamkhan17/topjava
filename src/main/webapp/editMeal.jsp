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
        .hidden{
            display: none;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>
<form method="POST" action='meals' name="formCUMeal">
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <table>
        <tr class="hidden">
            <td>Id</td>
            <td>
                <input type="text" name="id" value="<c:out value="${meal.id}" />"/>
            </td>
        </tr>
        <tr>
        <tr>
            <td>Calories</td>
            <td>
                <input type="text" name="calories" value="<c:out value="${meal.calories}" />"/>
            </td>
        </tr>
        <tr>
            <td>Description</td>
            <td>
                <input type="text" name="description" value="<c:out value="${meal.description}" />"/>
            </td>
        </tr>
        <tr>
            <td>Date</td>
            <td>
                <input type="datetime-local" name="date" value="<c:out value="${meal.dateTime}" />"/>
            </td>
        </tr>
    </table>
    <input class = "submit" type="submit" value="Submit"/>
</form>
</body>
</html>
