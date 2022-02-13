package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MealRepositoryImpl implements MealRepository<Meal> {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    public List<Meal> meals = new ArrayList<>();
    public static final Integer CALORIES_PER_DAY = 2000;

    {
        meals.addAll(Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        ));
        for (Meal meal : meals) {
            this.save(meal);
        }
    }

    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }

        return repository.computeIfPresent(meal.getId(), new BiFunction<Integer, Meal, Meal>() {
            @Override
            public Meal apply(Integer id, Meal oldMeal) {
                return meal;
            }
        });
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }


   /* @Override
    public List<Meal> getMeals() {
        return meals;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public Meal getMealById(int id) {
        return meals.stream().filter(meal -> meal.getId() == id).findAny().orElse(null);
    }

    public void deleteMeal(int id) {
        meals.removeIf(meal -> meal.getId() == id);
    }

    public void updateMeal(int id, Meal updateMeal) {
        Meal mealToUpdate = getMealById(id);
        mealToUpdate.setDateTime(updateMeal.getDateTime());
        mealToUpdate.setCalories(updateMeal.getCalories());
        mealToUpdate.setDescription(updateMeal.getDescription());
    }*/
}
