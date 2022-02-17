package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

// TODO add userId

public interface MealRepository {
    // null if updated meal does not belong to userId
    Meal save(Meal meal, int id);

    // false if meal does not belong to userId
    boolean delete(int id, int userId);

    // null if meal does not belong to userId
    Meal get(int id, int userId);

    Collection<Meal> getAll(LocalDateTime startDate, LocalTime startTime, LocalDateTime endDate, LocalTime endTime, int userId);

    // ORDERED dateTime desc
    Collection<Meal> getAll();
}
