package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealsDAO<T> {
    List<T> getMeals();
    void addMeal(Meal meal);
    T getMealById(int id);
    void deleteMeal(int id);
    void updateMeal(int id, Meal updateMeal);

}
