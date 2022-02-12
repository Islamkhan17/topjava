package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static String INSERT_OR_EDIT = "/editMeal.jsp";
    private static String LIST_MEALS = "/meals.jsp";
    private MealRepository<Meal> mealsDAO;

    public MealServlet() {
        super();
        log.debug("MealServlet object create");
        mealsDAO = new MealRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("forward to meal");

        List<MealTo> listMealTo = MealsUtil.filteredByStreamsWithoutTime(mealsDAO.getMeals(), MealRepositoryImpl.CALORIES_PER_DAY);
        req.setAttribute("FORMATTER", DATE_TIME_FORMATTER);
        req.setAttribute("mealsTo", listMealTo);

        String forward = "";
        String action = req.getParameter("action");

        if (action == null) {
            log.debug("action = " + null);

            forward = LIST_MEALS;

        } else if (action.equalsIgnoreCase("remove")) {
            log.debug("action = " + action);

            int mealId = Integer.parseInt(req.getParameter("mealId"));
            mealsDAO.deleteMeal(mealId);
            resp.sendRedirect("meals");
            return;

        } else if (action.equalsIgnoreCase("edit")) {
            log.debug("action = " + action);

            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            Meal meal = mealsDAO.getMealById(mealId);
            req.setAttribute("meal", meal);

        } else {
            log.debug("action = " + action);
            forward = INSERT_OR_EDIT;
        }

        log.debug("forward to " + forward);
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int mealId = 0;
        try {
            mealId = Integer.parseInt(req.getParameter("mealId"));

        } catch (NumberFormatException e){
            log.debug("mealId is empty create new meal");
        }
        int calories = Integer.parseInt(req.getParameter("calories"));
        String description = req.getParameter("description");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("date"));
        Meal meal = mealsDAO.getMealById(mealId);
        if (meal == null) {
            meal = new Meal(MealRepositoryImpl.ID++, dateTime, description, calories);
            mealsDAO.addMeal(meal);
        } else {
            meal.setDescription(description);
            meal.setCalories(calories);
            meal.setDateTime(dateTime);
        }

        req.setAttribute("FORMATTER", DATE_TIME_FORMATTER);
        req.setAttribute("mealsTo",
                MealsUtil.filteredByStreamsWithoutTime(mealsDAO.getMeals(), MealRepositoryImpl.CALORIES_PER_DAY));
        RequestDispatcher view = req.getRequestDispatcher(LIST_MEALS);
        view.forward(req, resp);
    }
}
