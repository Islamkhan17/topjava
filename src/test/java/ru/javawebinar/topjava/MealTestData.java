package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int GUEST_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 10;

    public static final Meal userMeal1 = new Meal(START_SEQ + 3, LocalDateTime.of(2020, Month.JANUARY, 30, 7, 30), "Завтрак", 1000);
    public static final Meal adminMeal1 = new Meal(START_SEQ + 10,LocalDateTime.of(2020, Month.FEBRUARY, 2, 10, 0), "Завтрак админа", 1200);
    public static final Meal adminMeal2 = new Meal(START_SEQ + 11,LocalDateTime.of(2020, Month.FEBRUARY, 2, 20, 0), "Ужин админа", 700);
    public static final Meal userMeal4 = new Meal(LocalDateTime.of(2021, Month.JANUARY, 2, 0, 0), "Еда на граничное значение", 200);
    public static final Meal userMeal5 = new Meal(LocalDateTime.of(2021, Month.JANUARY, 3, 10, 0), "Завтрак", 1400);
    public static final Meal userMeal6 = new Meal(LocalDateTime.of(2021, Month.JANUARY, 4, 20, 0), "Ужин", 700);


    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, Month.JANUARY, 4, 20, 0), "Ужин", 700);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal1);
        updated.setDateTime(LocalDateTime.of(2021, Month.JANUARY, 4, 20, 0));
        updated.setDescription("UpdatedDescription");
        updated.setCalories(777);
        return updated;
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
