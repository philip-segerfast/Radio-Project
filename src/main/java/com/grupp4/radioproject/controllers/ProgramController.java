package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping("/rest/programs/{page}")
    public List<Program> getAllPrograms(@PathVariable int page) {
        var programs = programService.getAllProgramsAtPage(page);
        return programs;
    }

}
