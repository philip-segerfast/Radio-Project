package com.grupp4.radioproject.services;

import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.utils.ConsoleColor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.grupp4.radioproject.utils.PrintUtils.printlnc;

@Service
public class ProgramService {

    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * Contains all programs in the Api (with fields limited to the contents in the Program class)
     */
    public static List<Program> allPrograms;

    @PostConstruct
    private void loadProgramsFromApi() {
        // Creates and starts a timer that will retrieve all programs from the Radio API once every 10 minutes.
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            allPrograms = getAllPrograms();
        }, 0, 10, TimeUnit.MINUTES);
    }

    /**
     * Gets a list of programs from the SR API
     * @param pageNumber Which page to get. Contains 10 elements each by default.
     * @return A list of programs from the SR API
     */
    public List<Program> getAllProgramsAtPage(int pageNumber) {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/programs?format=json&page=" + pageNumber;
        Map response = template.getForObject(URL, Map.class);

        List<Map> programsMap = (List<Map>) response.get("programs");
        List<Program> programs = new ArrayList<>();

        if(programsMap == null)
            return null;

        for(Map programMap : programsMap) {
            int programId = Integer.parseInt(programMap.get("id").toString());
            String programName = programMap.get("name").toString();
            String programDescription = programMap.get("description").toString();

            Map channelMap = (Map) programMap.get("channel");
            int channelId = Integer.parseInt(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();

            Channel channel = new Channel(channelId, channelName);
            Program program = new Program(programId, programName, channel, programDescription);

            programs.add(program);
        }

        return programs;
    }

    /**
     * Get's exactly ALL programs there is in the API.
     * Don't call outside this method.
     * To access all programs, use the static variable all.
     * @return All programs in the entire API.
     */
    private List<Program> getAllPrograms() {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/programs?format=json&pagination=false";
        Map response = template.getForObject(URL, Map.class);

        List<Map> programsMap = (List<Map>) response.get("programs");
        List<Program> programs = new ArrayList<>();

        if(programsMap == null)
            return null;

        for(Map programMap : programsMap) {
            int programId = Integer.parseInt(programMap.get("id").toString());
            String programName = programMap.get("name").toString();
            String programDescription = programMap.get("description").toString();

            Map channelMap = (Map) programMap.get("channel");
            int channelId = Integer.parseInt(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();

            Channel channel = new Channel(channelId, channelName);
            Program program = new Program(programId, programName, channel, programDescription);

            programs.add(program);
        }

        printlnc("Retrieved: " + programs.size() + " programs from SR API", ConsoleColor.GREEN);
        return programs;
    }


}
