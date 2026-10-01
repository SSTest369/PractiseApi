package com.almosafer.utils;

import java.time.LocalDate;

public final class TestData {

    private TestData() {}

    // Computed once when the class loads, so every test in a run uses the same dates.
    // LocalDate.toString() already gives yyyy-MM-dd.
    public static final String CHECK_IN = LocalDate.now()
            .plusDays(Integer.getInteger("checkInDaysAhead", 30)).toString();

    public static final String CHECK_OUT = LocalDate.parse(CHECK_IN)
            .plusDays(Integer.getInteger("nights", 2)).toString();

    public static final String QUERY = "Dubai";
    
    public static final long HOTEL_ID = 145333;

    // For tests that need different dates, e.g. a negative test with past dates
    public static String daysFromToday(int days) {
        return LocalDate.now().plusDays(days).toString();
    }
}