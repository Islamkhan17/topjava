package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(START_SEQ + 3, USER_ID);
        assertMatch(meal, userMeal1);
    }

    @Test
    public void getAnotherUserMeal() {
        assertThrows(NotFoundException.class, () -> service.get(START_SEQ + 3, GUEST_ID));
    }


    @Test
    public void delete() {
        service.delete(START_SEQ + 4, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(START_SEQ + 4, USER_ID));
    }

    @Test
    public void deleteAnotherUserMeal() {
        assertThrows(NotFoundException.class, () -> service.delete(START_SEQ + 4, GUEST_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> filteredMeal = service.getBetweenInclusive(LocalDate.of(2020,Month.FEBRUARY,2),LocalDate.of(2020,Month.FEBRUARY,2),ADMIN_ID);
        assertMatch(filteredMeal, adminMeal1, adminMeal2);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(ADMIN_ID);
        assertMatch(all, adminMeal1, adminMeal2);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(START_SEQ + 3, USER_ID), getUpdated());
    }

    @Test
    public void updateAnotherUserMeal() {
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, GUEST_ID));
    }


    @Test
    public void create() {
        Meal created = service.create(getNew(), GUEST_ID);
        System.out.println(created);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, GUEST_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 07, 30), "дупликатЗавтрака", 1100), GUEST_ID));
    }
}