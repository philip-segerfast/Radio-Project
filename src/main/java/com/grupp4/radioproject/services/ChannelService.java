package com.grupp4.radioproject.services;

import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.entities.ProgramCategory;
import com.grupp4.radioproject.repositories.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.grupp4.radioproject.utils.PrintUtils.printDebug;
import static com.grupp4.radioproject.utils.PrintUtils.printInfo;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepo channelRepo;

    public List<Channel> getAllChannels() {
        RestTemplate template = new RestTemplate();
        String URL = "http://api.sr.se/api/v2/channels?format=json";
        Map response = template.getForObject(URL, Map.class);

        List<Map> channelsMap = (List<Map>) response.get("channels");
        List<Channel> channels = new ArrayList<>();

        if(channelsMap == null)
            return null;

        for(Map channelMap : channelsMap) {
            long channelId = Integer.parseInt(channelMap.get("id").toString());
            String channelName = channelMap.get("name").toString();
            String tagline = channelMap.get("tagline").toString();
            Channel channel = new Channel(channelId, channelName, tagline);
            channels.add(channel);
        }

        return channels;
    }

    public Channel getChannelById(long id) {
        RestTemplate template = new RestTemplate();
        String URL = String.format("http://api.sr.se/api/v2/channels/%d?format=json", id);
        Map response = template.getForObject(URL, Map.class);

        Map channelMap = (Map) response.get("channel");

        if(channelMap == null)
            return null;

        long channelId = Integer.parseInt(channelMap.get("id").toString());
        String channelName = channelMap.get("name").toString();
        String tagline = channelMap.get("tagline").toString();

        return new Channel(channelId, channelName, tagline);
    }
    public boolean registerChannel(long channelId) {
        printDebug("PROBLEM HÄR?");
        if(channelRepo.findById(channelId).isEmpty()) { /////////////////////////////////////////////////////////////// PROBLEM HÄR. FINDBYID FUNKAR EJ!!!!!!!!!!!!!!!!!!
            printDebug("ELLER INTE?");
            // Channel doesn't exist in database
            printInfo("Saving channel...");
            channelRepo.customSave(channelId);
            printInfo("New Channel registered in database.");
            return true;
        }
        return false;
    }

}
