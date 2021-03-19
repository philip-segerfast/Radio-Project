package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.entities.ProgramCategory;
import com.grupp4.radioproject.services.ProgramCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProgramCategoryController {

    @Autowired
    private ProgramCategoryService categoryService;

    @GetMapping("/rest/categories")
    public List<ProgramCategory> getAllCategories() {
        return categoryService.getAllCategories();
    }

}
