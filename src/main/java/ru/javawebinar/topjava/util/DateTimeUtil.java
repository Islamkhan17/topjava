package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /*public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }*/

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T lt, T startTime, T endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static void main(String[] args) {
       /* LocalTime localStart = LocalTime.parse("22:00");
        LocalTime localTime = LocalTime.parse("23:00");
        LocalTime localEnd = LocalTime.parse("23:59");
        LocalDateTime localDateStart = LocalDateTime.parse("2022-01-01T00:00");
        LocalDateTime localDateTime = LocalDateTime.parse("2022-01-04T00:00");
        LocalDateTime localDateEnd = LocalDateTime.parse("2022-01-03T00:00");
        System.out.println(localDateStart);
        System.out.println(localDateEnd);
        System.out.println(localStart);
        System.out.println(localEnd);
        System.out.println(isBetweenHalfOpen(localTime,localStart,localEnd));*/
        LocalDate.now();
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

