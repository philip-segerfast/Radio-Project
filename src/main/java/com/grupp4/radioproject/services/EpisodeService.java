package com.grupp4.radioproject.services;

import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.entities.Episode;
import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.entities.ProgramCategory;
import com.grupp4.radioproject.repositories.EpisodeRepo;
import com.grupp4.radioproject.utils.ConsoleColor;
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

import static com.grupp4.radioproject.utils.PrintUtils.printColoredLine;

@Service
public class EpisodeService {
    @Autowired
    private EpisodeRepo episodeRepo;

    @Autowired
    private ProgramService programService;

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

            Episode episode = new Episode(episodeId, name, description, publishdateutc, url, programService.getProgramById(programId));
            programEpisodes.add(episode);
        }

        return programEpisodes;
    }

    public Episode registerEpisode(Episode episode) {
        return episodeRepo.save(episode);
    }

    /**
     * Gets an episode from SR's API
     */
    public Episode getEpisodeById(long id) {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/episodes/" + id + "?format=json";
        Map response = template.getForObject(URL, Map.class);

        Map episodeMap = (Map) response.get("episode");
        String title = episodeMap.get("title").toString();
        String description = episodeMap.get("description").toString();
        String publishdateutc = episodeMap.get("publishdateutc").toString();
        String url = episodeMap.get("url").toString();

        Map programMap = (Map) episodeMap.get("program");
        Program program = null;
        if(programMap != null) {
            long programId = Long.parseLong(programMap.get("id").toString());
            String programName = programMap.get("name").toString();
            program = new Program(programId, programName);
        }

        return new Episode(id, title, description, publishdateutc, url, program);
    }

    /**
     * Feeds <code>episodes</code> (which come directly from the database) with data from the API.
     * @return the same list but with objects fetched directly from the API.
     */
    public List<Episode> feedEpisodeInfo(List<Episode> episodes) {
        for(int i = 0; i < episodes.size(); i++) {
            long hungryEpisodeId = episodes.get(i).getEpisodeId();
            episodes.set(i, getEpisodeById(hungryEpisodeId));
        }
        return episodes;
    }
}
