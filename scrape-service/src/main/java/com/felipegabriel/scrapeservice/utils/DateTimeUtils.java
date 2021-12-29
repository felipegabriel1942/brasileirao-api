package com.felipegabriel.scrapeservice.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeUtils {

    public static LocalDate convertStringToDate(String string) {
        DateTimeFormatter dateFormatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)
                .withLocale(Locale.forLanguageTag("pt-BR"));

        return LocalDate.parse(string, dateFormatter);
    }

    public static LocalTime convertStringToTime(String string) {
        return LocalTime.parse(string);
    }
}
