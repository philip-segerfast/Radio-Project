package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.entities.Episode;
import com.grupp4.radioproject.entities.ScheduleEpisode;
import com.grupp4.radioproject.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping("/rest/episodes/{programId}")
    public List<Episode> getEpisodesListByProgram(@PathVariable long programId) {
        return episodeService.getEpisodesListByProgram(programId);
    }

    @GetMapping("/rest/episodes/id/{episodeId}")
    public Episode getEpisodeById(@PathVariable long episodeId) {
        return episodeService.getEpisodeById(episodeId);
    }
}
