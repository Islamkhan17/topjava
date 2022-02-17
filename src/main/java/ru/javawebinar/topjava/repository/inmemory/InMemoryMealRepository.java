package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    private static final Logger log = getLogger(InMemoryMealRepository.class);

    public static final List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, 1),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, 1),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, 1),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, 1),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, 1),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, 1),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 14, 0), "Обед1", 1000, 3),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 15, 0), "Обед2", 1500, 3),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 16, 0), "Обед3", 2500, 3),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 17, 0), "Обед4", 3500, 3),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, 1)
    );

    {
        int count = 0;
        for (Meal meal : InMemoryMealRepository.meals) {
            repository.put(count++, meal);
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.debug("meal.getUserId() = " + meal.getUserId() + "\n"
                + "userId = " + userId + "\n" +
                "meal.id = " + meal.getId());
        if (meal.getUserId() == userId) {
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                repository.put(meal.getId(), meal);
                return meal;
            }
            // handle case: update, but not present in storage
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return userId == repository.get(id).getUserId() ? repository.remove(id) != null : false;
    }

    @Override
    public Meal get(int id, int userId) {
        return userId == repository.get(id).getUserId() ? repository.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(LocalDateTime startDate, LocalTime startTime, LocalDateTime endDate, LocalTime endTime, int userId) {
        Collection<Meal> filterList = getAll().stream().filter(meal -> meal.getUserId() == userId).collect(Collectors.toList());
        if (startDate != null && endDate != null) {
            filterList = filterList.stream().filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime(), startDate, endDate)).collect(Collectors.toList());
        }
        if (startTime != null && endTime != null) {
            filterList = filterList.stream().filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime)).collect(Collectors.toList());
        }
        return filterList;
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().stream().sorted(Comparator.comparing(Meal::getDate)).collect(Collectors.toList());
    }
}

