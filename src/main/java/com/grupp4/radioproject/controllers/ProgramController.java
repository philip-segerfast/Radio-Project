package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

}
