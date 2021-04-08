package com.grupp4.radioproject.services;

import com.grupp4.radioproject.entities.Episode;
import com.grupp4.radioproject.entities.ProgramCategory;
import com.grupp4.radioproject.repositories.EpisodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EpisodeService {
    @Autowired
    private EpisodeRepo episodeRepo;


    public List<Episode> getEpisodesListByProgram(long programId) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/episodes/index?format=json&programid=";
        Map response = restTemplate.getForObject(URL + programId, Map.class);

        List<Map> episodesMap = (List<Map>) response.get("episodes");
        List<Episode> programEpisodes = new ArrayList<>();

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendLiteral("/Date(")
                .appendValue(ChronoField.INSTANT_SECONDS)
                .appendValue(ChronoField.MILLI_OF_SECOND, 3)
                .appendLiteral(")/")
                .toFormatter();

        if(episodesMap == null)
            return null;

        for(Map episodeMap : episodesMap) {
            long episodeId = Long.parseLong(episodeMap.get("id").toString());
            String name = episodeMap.get("title").toString();
            String description = episodeMap.get("description").toString();
            String publishdateutc = episodeMap.get("publishdateutc").toString();
            String url = episodeMap.get("url").toString();

            Instant dateTime = formatter.parse(publishdateutc, Instant::from);
            String formattedDate = dateTime.toString();
            publishdateutc = formattedDate.replace("T"," ").replace("Z","");

            Episode episode = new Episode(episodeId, name, description, publishdateutc, url);
            programEpisodes.add(episode);
        }

        return programEpisodes;
    }
}
