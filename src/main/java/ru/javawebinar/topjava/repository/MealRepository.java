package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.List;

public interface MealRepository<T> {
  /*  List<T> getMeals();
    void addMeal(Meal meal);
    T getMealById(int id);
    void deleteMeal(int id);
    void updateMeal(int id, Meal updateMeal);*/


    // null if not found, when updated
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id);

    Collection<Meal> getAll();
}
