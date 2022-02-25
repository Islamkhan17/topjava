DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2020-01-30T07:30', 'Завтрак' , '1000'),
       (100000,'2020-01-30T12:30', 'Обед' , '700'),
       (100000,'2020-01-30T16:30', 'Полдник' , '400'),
       (100000,'2020-01-30T19:30', 'Ужин' , '300'),
       (100000,'2020-01-31T09:30', 'Breakfast' , '800'),
       (100000,'2020-01-31T13:30', 'Lunch' , '1000'),
       (100000,'2020-01-31T20:30', 'Dinner' , '200'),
       (100001,'2020-02-02T10:00', 'Завтрак админа' , '1200'),
       (100001,'2020-02-02T20:00', 'Ужин админа' , '700');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);
