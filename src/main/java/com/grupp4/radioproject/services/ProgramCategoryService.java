package com.grupp4.radioproject.services;

import com.grupp4.radioproject.entities.ProgramCategory;
import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.repositories.ProgramCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProgramCategoryService {

    @Autowired
    ProgramCategoryRepo programCategoryRepo;

    public List<ProgramCategory> getAllCategories() {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/programcategories?format=json";
        Map response = template.getForObject(URL, Map.class);

        List<Map> categoriesMap = (List<Map>) response.get("programcategories");
        List<ProgramCategory> programCategories = new ArrayList<>();

        if(categoriesMap == null)
            return null;

        for(Map categoryMap : categoriesMap) {
            long id = Long.parseLong(categoryMap.get("id").toString());
            String name = categoryMap.get("name").toString();
            ProgramCategory category = new ProgramCategory(id, name);
            programCategories.add(category);
        }

        return programCategories;
    }

    public ProgramCategory registerProgramCategory(ProgramCategory programCategory) {
        return programCategoryRepo.save(programCategory);
    }

}
