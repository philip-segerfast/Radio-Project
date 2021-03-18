package com.grupp4.radioproject.services;

import static com.grupp4.radioproject.api.ApiCommon.*;
import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.entities.Program;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
{
    "description": "Sport, P4 Skaraborg, Sveriges Radio.",
    "email": "sporten.skaraborg@sverigesradio.se",
    "phone": "",
    "programurl": "https://sverigesradio.se/sida/gruppsida.aspx?programid=97&grupp=3840",
    "programslug": "sportenp4skaraborg",
    "programimage": "https://static-cdn.sr.se/images/content/sverigesradiologga.jpg?preset=api-default-square",
    "programimagetemplate": "https://static-cdn.sr.se/images/content/sverigesradiologga.jpg",
    "programimagewide": "https://static-cdn.sr.se/images/35/3116563_176_132.jpg?preset=api-default-rectangle",
    "programimagetemplatewide": "https://static-cdn.sr.se/images/35/3116563_176_132.jpg",
    "socialimage": "https://static-cdn.sr.se/images/content/sverigesradiologga.jpg?preset=api-default-square",
    "socialimagetemplate": "https://static-cdn.sr.se/images/content/sverigesradiologga.jpg",
    "socialmediaplatforms": [],
    "channel": {
        "id": 208,
        "name": "P4 Skaraborg"
    },
    "archived": false,
    "hasondemand": true,
    "haspod": false,
    "responsibleeditor": "Kajsa Hallberg",
    "id": 35,
    "name": "Sporten P4 Skaraborg"
}
*/

@Service
public class ProgramService {

    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * Gets a list of programs from the SR API
     * @param pageNumber Which page to get. Contains 10 elements each by default.
     * @return A list of programs from the SR API
     */
    public List<Program> getAllPrograms(int pageNumber) {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/programs?format=json&page=" + pageNumber;
        System.out.println("------------------------------------------------1");
        Map response = template.getForObject(URL, Map.class);
        System.out.println("------------------------------------------------2");

        System.out.println("2");
        List<Map> programsMap = (List<Map>) response.get("programs");
        List<Program> programs = new ArrayList<>();

        if(programsMap == null)
            return null;

        for(Map programMap : programsMap) {
            System.out.println("3");
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


}
