package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.entities.ScheduleEpisode;
import com.grupp4.radioproject.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping("/rest/programs/{page}")
    public List<Program> getAllPrograms(@PathVariable int page) {
        return programService.getAllProgramsAtPage(page);
    }

    @GetMapping("/rest/programs/search/{phrase}")
    public List<Program> getSearchedPrograms(@PathVariable String phrase) {
        return programService.searchPrograms(phrase);
    }

    @GetMapping("/rest/programs/programcategory/{id}")
    public List<Program> getAllProgramsByCategory(@PathVariable long id) {
        return programService.getProgramsByCategory(id);
    }

    @GetMapping("/rest/programs/channel/{id}")
    public List<Program> getProgramsByChannels(@PathVariable long id){
        return programService.getProgramsByChannel(id);
    }

    @GetMapping("/rest/programs/tableau/{id}")
    public List<ScheduleEpisode> getScheduleByChannel(@PathVariable long id) {
        return programService.getScheduleByChannel(id);
    }

}
