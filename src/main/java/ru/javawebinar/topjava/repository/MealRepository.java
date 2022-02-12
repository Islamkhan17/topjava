package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository<T> {
    List<T> getMeals();
    void addMeal(Meal meal);
    T getMealById(int id);
    void deleteMeal(int id);
    void updateMeal(int id, Meal updateMeal);

}
