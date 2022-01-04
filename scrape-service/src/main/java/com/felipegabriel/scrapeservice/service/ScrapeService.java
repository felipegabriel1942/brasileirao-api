package com.felipegabriel.scrapeservice.service;

import com.felipegabriel.scrapeservice.dto.MatchDTO;
import com.felipegabriel.scrapeservice.utils.*;

import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ScrapeService {

    @Value("${scrapeRequest.userAgent}")
    public String userAgent;

    @Value("${scrapeRequest.baseUrl}")
    public String baseUrl;

    @Value("${scrapeRequest.delayBeforeRequest}")
    public Integer delayBeforeRequest;

    @Value("${brasileirao.totalMatchesPerSeason}")
    public Integer totalMatchesPerSeason;

    private static final Logger log = LoggerFactory.getLogger(ScrapeService.class);

    public List<MatchDTO> findMatches(Integer season, String division) {
        return IntStream
                .range(1, totalMatchesPerSeason + 1)
                .mapToObj(matchNumber -> scrapeMatchHtmlPage(season, matchNumber, division))
                .map(this::convertDocumentToMatchDTO)
                .collect(Collectors.toList());
    }

    public List<MatchDTO> findMatchesByMatchNumbers(Integer season, List<Integer> matchNumbers, String division) {
        return matchNumbers.stream()
                .map(matchNumber -> scrapeMatchHtmlPage(season, matchNumber, division))
                .map(this::convertDocumentToMatchDTO)
                .collect(Collectors.toList());
    }

    // TODO: IMPLEMENT ERROR TREATMENT WHEN HTML PAGE NOT FOUND.
    private Document scrapeMatchHtmlPage(int season, int matchNumber, String division) {
        Document htmlPage = null;

        try {
            String url = baseUrl + division.toLowerCase() + "/"  + season + "/" + matchNumber;
            Thread.sleep(delayBeforeRequest);
            htmlPage = Jsoup.connect(url).userAgent(userAgent).get();
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }

        return htmlPage;
    }

    private MatchDTO convertDocumentToMatchDTO(Document htmlPage) {
        MatchDTO matchDTO = MatchDTO
                .builder()
                .stadium(ScrapUtils.extractStadium(htmlPage))
                .date(ScrapUtils.extractDate(htmlPage))
                .homeTeam(ScrapUtils.extractHomeTeamName(htmlPage))
                .homeTeamGoals(ScrapUtils.extractHomeTeamGoals(htmlPage))
                .visitorTeam(ScrapUtils.extractVisitorTeamName(htmlPage))
                .visitorTeamGoals(ScrapUtils.extractVisitorTeamGoals(htmlPage))
                .round(ScrapUtils.extractRound(htmlPage))
                .matchNumber(ScrapUtils.extractMatchNumber(htmlPage))
                .division(ScrapUtils.extractDivision(htmlPage))
                .visitorTeamCrest(ScrapUtils.extractVisitorTeamCrest(htmlPage))
                .season(ScrapUtils.extractSeason(htmlPage))
                .homeTeamCrest(ScrapUtils.extractHomeTeamCrest(htmlPage)).build();

        log.info("Creating match: {}. ", matchDTO.toString());

        return matchDTO;
    }
}
