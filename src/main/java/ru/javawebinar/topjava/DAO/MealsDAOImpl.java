package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealsDAOImpl implements MealsDAO<Meal> {
    public List<Meal> meals = new ArrayList<>();
    public static final Integer CALORIES_PER_DAY = 2000;
    public static Integer ID = 1;

    {
        meals.addAll(Arrays.asList(
                new Meal(ID++,LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(ID++,LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(ID++,LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(ID++,LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(ID++,LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(ID++,LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(ID++,LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        ));
    }

    @Override
    public List<Meal> getMeals() {
        return meals;
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }

    public Meal getMealById(int id){
        return meals.stream().filter(meal -> meal.getId()==id).findAny().orElse(null);
    }

    public void deleteMeal(int id){
        meals.removeIf(meal -> meal.getId() == id);
    }

    public void updateMeal(int id, Meal updateMeal){
        Meal mealToUpdate = getMealById(id);
        mealToUpdate.setDateTime(updateMeal.getDateTime());
        mealToUpdate.setCalories(updateMeal.getCalories());
        mealToUpdate.setDescription(updateMeal.getDescription());
    }
}
