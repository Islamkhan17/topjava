package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        log.info("getAll");
        List<MealTo> result;
        if (authUserId() == 2) {
            result = MealsUtil.getTos(service.getAll(), authUserCaloriesPerDay());
        } else result = MealsUtil.getTos(service.getAll(null, null, null, null, authUserId()), authUserCaloriesPerDay());
        return result;
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public List<MealTo> getAll(LocalDateTime startDate, LocalTime startTime, LocalDateTime endDate, LocalTime endTime) {
        // check by userid ?
        log.info("getAll filtered, userId = " + authUserId());
        return MealsUtil.getTos(service.getAll(startDate, startTime, endDate, endTime, authUserId()), authUserCaloriesPerDay());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
//        Meal meal = new Meal(null, mealTo.getDateTime(), mealTo.getDescription(), mealTo.getCalories());
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", authUserId());
        service.delete(id, authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }
}