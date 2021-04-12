package com.grupp4.radioproject.services;

import com.grupp4.radioproject.entities.*;
import com.grupp4.radioproject.repositories.ChannelRepo;
import com.grupp4.radioproject.repositories.ProgramRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

import static com.grupp4.radioproject.utils.PrintUtils.*;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    ChannelRepo channelRepo;

    /**
     * Default for how many programs will be fetched at once with pagination set to true
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * Contains all programs in the Api (with fields limited to the contents in the Program class)
     */
    public static List<Program> allPrograms;

    @PostConstruct
    private void loadProgramsFromApi() {
        // Creates and starts a timer that will retrieve all programs from the Radio API once every 10 minutes.
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            printInfo("Retrieving programs from API...");
            var programs = getAllPrograms();
            allPrograms = programs;
            assert programs != null;
            printInfo("Retrieved: " + programs.size() + " programs from SR API");
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
        return getPrograms(template, URL);
    }

    /**
     * Get exactly ALL programs there is in the API.
     * Don't call outside this method.
     * To access all programs, use the static variable allPrograms.
     * @return All programs in the entire API.
     */
    private List<Program> getAllPrograms() {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/programs?format=json&pagination=false";
        return getPrograms(template, URL);
    }

    /**
     * Expects a collection of programs as the returned fetch of the specified URL.
     * Grabs everything and returns it.
     * @param template
     * @param URL
     * @return A collection of programs.
     */
    private List<Program> getPrograms(RestTemplate template, String URL) {
        Map response = template.getForObject(URL, Map.class);

        List<Map> programsMap = (List<Map>) response.get("programs");
        List<Program> programs = new ArrayList<>();

        if(programsMap == null)
            return null;

        for(Map programMap : programsMap) {
            long programId = Integer.parseInt(programMap.get("id").toString());
            String programName = programMap.get("name").toString();
            String programDescription = programMap.get("description").toString();

            Map channelMap = (Map) programMap.get("channel");
            long channelId = Integer.parseInt(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();

            // Program category
            Map categoryMap = (Map) programMap.get("programcategory");
            ProgramCategory category = null;
            if(categoryMap != null) {
                long categoryId = Long.parseLong(categoryMap.get("id").toString());
                String name = categoryMap.get("name").toString();
                category = new ProgramCategory(categoryId, name);
            }

            Channel channel = new Channel(channelId, channelName);
            Program program = new Program(programId, programName, channel, programDescription, category);

            programs.add(program);
        }

        return programs;
    }

    public List<Program> getProgramsByChannel(long id) {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/programs/index?format=json&channelid=";
        Map response = template.getForObject(URL + id, Map.class);

        List<Map> programsMap = (List<Map>) response.get("programs");
        List<Program> programs = new ArrayList<>();

        if(programsMap == null)
            return null;

        for(Map programMap : programsMap) {
            Long programId = Long.parseLong(programMap.get("id").toString());
            String programName = programMap.get("name").toString();
            String programDescription = programMap.get("description").toString();

            Map channelMap = (Map) programMap.get("channel");
            Long channelId = Long.parseLong(channelMap.get("id").toString());
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
        var response = template.getForObject(URL + id, Map.class);

        List<Map> categoriesMap = (List<Map>) response.get("programs");
        List<Program> programs = new ArrayList<>();

        if(categoriesMap == null)
            return null;

        for(Map categoryMap : categoriesMap) {
            long programId = Long.parseLong(categoryMap.get("id").toString());
            String programName = categoryMap.get("name").toString();
            String programDescription = categoryMap.get("description").toString();

            Map channelMap = (Map) categoryMap.get("channel");
            long channelId = Long.parseLong(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();

            Map programCategoryMap = (Map) categoryMap.get("programcategory");
            long categoryId = Long.parseLong(programCategoryMap.get("id").toString());
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
            long episodeId = 0;
            if(scheduleMap.get("episodeid") != null) {
                episodeId = Long.parseLong(scheduleMap.get("episodeid").toString());
            }
            String title = scheduleMap.get("title").toString();
            String description = scheduleMap.get("description").toString();
            String starttimeutc = scheduleMap.get("starttimeutc").toString();

            Instant dateTime = formatter.parse(starttimeutc, Instant::from);
            String formattedDate = dateTime.toString();
            starttimeutc = formattedDate.replace("T"," ").replace("Z","");

            Map channelMap = (Map) scheduleMap.get("channel");
            long channelId = Long.parseLong(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();

            Map programMap = (Map) scheduleMap.get("program");
            long programId = Long.parseLong(programMap.get("id").toString());
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
            long episodeId = 0;
            if(scheduleMap.get("episodeid") != null) {
                episodeId = Long.parseLong(scheduleMap.get("episodeid").toString());
            }
            String title = scheduleMap.get("title").toString();
            String description = scheduleMap.get("description").toString();
            String starttimeutc = scheduleMap.get("starttimeutc").toString();

            Instant dateTime = formatter.parse(starttimeutc, Instant::from);
            String formattedDate = dateTime.toString();
            starttimeutc = formattedDate.replace("T"," ").replace("Z","");

            Map channelMap = (Map) scheduleMap.get("channel");
            long channelId = Long.parseLong(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();

            Map programMap = (Map) scheduleMap.get("program");
            long programId = Long.parseLong(programMap.get("id").toString());
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

    public Program getProgramById (long id) {
        for(Program program: allPrograms) {
            if(program.getId() == id ) {
                return program;
            }
        }
        return null;
    }

    /**
     * Registers a program in the database. Takes for granted that the related channel is already saved.
     * @param program
     */
    public void registerProgram(Program program) {

        if(programRepo.findById(program.getId()).isEmpty()) {
            // Program doesn't exist in database
            printInfo("Saving program...");
            programRepo.save(program);
            printInfo("New Program registered in database.");
        }
    }

    public List<ScheduleEpisode> getScheduleForProgram(long programId) {
        // 1. ta reda på vilken kanal programmet tillhör
        // 2. Hämta ut tablå för kanalen
        // 3. Filtrera endast ut episoder som tillhör det här programmet
        Program program = getProgramById(programId);
        Channel channel = program.getChannel();
        List<ScheduleEpisode> channelSchedule = getScheduleByChannel(channel.getId());
        List<ScheduleEpisode> programSchedule = new ArrayList<>();
        for(ScheduleEpisode episode : channelSchedule) {
            if(episode.getProgram().getId() == programId) {
                programSchedule.add(episode);
            }
        }

        return programSchedule;
    }

    /**
     * Feeds <code>programs</code> (which come directly from the database) with data from the API.
     * @return the same list but with objects fetched directly from the API.
     */
    public List<Program> feedProgramInfo(List<Program> programs) {
        for(int i = 0; i < programs.size(); i++) {
            long hungryProgramId = programs.get(i).getId();
            programs.set(i, getProgramById(hungryProgramId));
        }
        return programs;
    }
}
