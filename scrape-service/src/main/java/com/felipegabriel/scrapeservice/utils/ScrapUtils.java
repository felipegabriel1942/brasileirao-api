package com.felipegabriel.scrapeservice.utils;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Objects;

public class ScrapUtils {

    public static String extractStadium(Document page) {
        Elements stadium = page.select("span.text-2");
        return Objects.nonNull(stadium) ? stadium.get(0).text().split("-")[0] : null;
    }

    public static LocalDateTime extractDate(Document page) {
        Elements elements = page.select("span.text-2");

        if (Objects.isNull(elements)) {
            return null;
        }

        LocalDate date = DateTimeUtils.convertStringToDate(elements.get(1).text().split(",")[1].trim());

        LocalTime time = DateTimeUtils.convertStringToTime(elements.get(2).text());

        return LocalDateTime.of(date, time);
    }

    public static String extractHomeTeamName(Document page) {
        Elements teamName = page.getElementsByClass("time-nome");
        return Objects.nonNull(teamName) ? teamName.get(0).text() : null;
    }

    public static String extractVisitorTeamName(Document page) {
        Elements teamName = page.getElementsByClass("time-nome");
        return Objects.nonNull(teamName) ? teamName.get(1).text() : null;
    }

    public static Integer extractHomeTeamGoals(Document page) {
        Elements goals = page.getElementsByClass("time-gols");
        return Objects.nonNull(goals) ? Integer.parseInt(goals.get(0).text()) : null;
    }

    public static Integer extractVisitorTeamGoals(Document page) {
        Elements goals = page.getElementsByClass("time-gols");
        return Objects.nonNull(goals) ? Integer.parseInt(goals.get(2).text()) : null;
    }

    public static String extractDivision(Document page) {
        Elements division = page.getElementsByTag("h3");
        return Objects.nonNull(division) ? division.get(0).text().split("-")[1].substring(6) : null;
    }

    public static Integer extractRound(Document page) {
        Integer MatchDTONumber = extractMatchNumber(page);
        return Objects.nonNull(MatchDTONumber) ? Math.round((((MatchDTONumber - 1) / 10) + 1)) : null;
    }

    public static Integer extractMatchNumber(Document page) {
        Elements MatchDTONumber = page.getElementsByClass("color-white");
        return Objects.nonNull(MatchDTONumber) ? Integer.parseInt(MatchDTONumber.get(2).text().replace("Jogo: ", "")) : null;
    }

    public static String extractVisitorTeamCrest(Document page) {
        Elements teamCrest = page.getElementsByClass("time-escudo");
        return Objects.nonNull(teamCrest) ? teamCrest.get(1).getElementsByTag("img").attr("src") : null;
    }

    public static String extractHomeTeamCrest(Document page) {
        Elements teamCrest = page.getElementsByClass("time-escudo");
        return Objects.nonNull(teamCrest) ? teamCrest.get(0).getElementsByTag("img").attr("src") : null;
    }
}

