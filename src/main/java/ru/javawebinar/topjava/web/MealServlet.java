package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealsDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public static List<Meal> listMeal = new MealsDAO().getMeals();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("forward to meal");
        List<MealTo> listMealTo = MealsUtil.filteredByStreamsWithoutTime(listMeal, MealsDAO.CALORIES_PER_DAY);
        listMealTo.forEach(System.out::println);
        req.setAttribute("mealsTo",listMealTo);

        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
