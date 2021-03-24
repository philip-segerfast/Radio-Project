package com.grupp4.radioproject.services;

import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.entities.ProgramCategory;
import com.grupp4.radioproject.entities.ScheduleEpisode;
import com.grupp4.radioproject.utils.ConsoleColor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
     * Search for a program based on name and description. Case insensitive.
     * @param phrase Search phrase
     * @return List of programs matching search phrase
     */
    public List<Program> searchPrograms(String phrase) {
        return allPrograms.stream()
                // Only return programs that contains the search phrase in name or description
                .filter(program -> program.getName().toLowerCase().contains(phrase.toLowerCase())
                        ||
                        program.getDescription().toLowerCase().contains(phrase.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of programs from the SR API
     * @param pageNumber Which page to get. Contains 10 elements each by default
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
     * To access all programs, use the static variable allPrograms.
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

    public List<Program> getProgramsByChannel(long id){
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/programs/index?format=json&channelid=";
        Map response = template.getForObject(URL + id, Map.class);

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


    public List<Program> getProgramsByCategory(long id) {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/programs/index?format=json&pagination=false&programcategoryid=";
        Map response = template.getForObject(URL + id, Map.class);

        List<Map> categoriesMap = (List<Map>) response.get("programs");
        List<Program> programs = new ArrayList<>();

        if(categoriesMap == null)
            return null;

        for(Map categoryMap : categoriesMap) {
            int programId = Integer.parseInt(categoryMap.get("id").toString());
            String programName = categoryMap.get("name").toString();
            String programDescription = categoryMap.get("description").toString();

            Map channelMap = (Map) categoryMap.get("channel");
            int channelId = Integer.parseInt(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();

            Map programCategoryMap = (Map) categoryMap.get("programcategory");
            int categoryId = Integer.parseInt(programCategoryMap.get("id").toString());
            String categoryName = programCategoryMap.get("name").toString();

            Channel channel = new Channel(channelId, channelName);
            ProgramCategory programCategory = new ProgramCategory(categoryId, categoryName);
            Program program = new Program(programId, programName, channel, programDescription, programCategory);

            programs.add(program);
        }

        return programs;
    }

    public List<ScheduleEpisode> getScheduleByChannel(long id) {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/scheduledepisodes?pagination=false&format=json&channelid=";
        Map response = template.getForObject(URL + id, Map.class);

        List<Map> schedulesMap = (List<Map>) response.get("schedule");
        List<ScheduleEpisode> scheduleEpisodes = new ArrayList<>();


        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendLiteral("/Date(")
                .appendValue(ChronoField.INSTANT_SECONDS)
                .appendValue(ChronoField.MILLI_OF_SECOND, 3)
                .appendLiteral(")/")
                .toFormatter();

        if(schedulesMap == null) return null;

        for(Map scheduleMap : schedulesMap) {
            int episodeId = 0;
            if(scheduleMap.get("episodeid") != null) {
                episodeId = Integer.parseInt(scheduleMap.get("episodeid").toString());
            }
            String title = scheduleMap.get("title").toString();
            String description = scheduleMap.get("description").toString();
            String starttimeutc = scheduleMap.get("starttimeutc").toString();

            Instant dateTime = formatter.parse(starttimeutc, Instant::from);
            String formattedDate = dateTime.toString();
            starttimeutc = formattedDate.replace("T"," ").replace("Z","");

            Map channelMap = (Map) scheduleMap.get("channel");
            int channelId = Integer.parseInt(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();

            Map programMap = (Map) scheduleMap.get("program");
            int programId = Integer.parseInt(programMap.get("id").toString());
            String programName = null;
            if(programMap.get("name") != null) {
                programName = programMap.get("name").toString();
            }

            Channel channel = new Channel(channelId, channelName);
            Program program = new Program(programId, programName);
            ScheduleEpisode scheduleEpisode = new ScheduleEpisode(episodeId, title, description, starttimeutc, channel, program);

            scheduleEpisodes.add(scheduleEpisode);
        }
        return scheduleEpisodes;
    }

    public List<ScheduleEpisode> getScheduleByChannelAndDate(long id, String date) {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/scheduledepisodes?pagination=false&format=json&channelid=";
        Map response = template.getForObject(URL + id + "&date=" + date, Map.class);

        List<Map> schedulesMap = (List<Map>) response.get("schedule");
        List<ScheduleEpisode> scheduleEpisodes = new ArrayList<>();

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendLiteral("/Date(")
                .appendValue(ChronoField.INSTANT_SECONDS)
                .appendValue(ChronoField.MILLI_OF_SECOND, 3)
                .appendLiteral(")/")
                .toFormatter();

        if(schedulesMap == null) return null;

        for(Map scheduleMap : schedulesMap) {
            int episodeId = 0;
            if(scheduleMap.get("episodeid") != null) {
                episodeId = Integer.parseInt(scheduleMap.get("episodeid").toString());
            }
            String title = scheduleMap.get("title").toString();
            String description = scheduleMap.get("description").toString();
            String starttimeutc = scheduleMap.get("starttimeutc").toString();

            Instant dateTime = formatter.parse(starttimeutc, Instant::from);
            String formattedDate = dateTime.toString();
            starttimeutc = formattedDate.replace("T"," ").replace("Z","");

            Map channelMap = (Map) scheduleMap.get("channel");
            int channelId = Integer.parseInt(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();

            Map programMap = (Map) scheduleMap.get("program");
            int programId = Integer.parseInt(programMap.get("id").toString());
            String programName = null;
            if(programMap.get("name") != null) {
                programName = programMap.get("name").toString();
            }

            Channel channel = new Channel(channelId, channelName);
            Program program = new Program(programId, programName);
            ScheduleEpisode scheduleEpisode = new ScheduleEpisode(episodeId, title, description, starttimeutc, channel, program);

            scheduleEpisodes.add(scheduleEpisode);
        }
        return scheduleEpisodes;
    }
}


