package com.grupp4.radioproject.services;

import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.entities.ProgramCategory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChannelService {

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

}
