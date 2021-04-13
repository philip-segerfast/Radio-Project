package com.grupp4.radioproject.services;

import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.repositories.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.grupp4.radioproject.utils.PrintUtils.printDebug;

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
            String imageUrl = channelMap.get("image").toString();
            Channel channel = new Channel(channelId, channelName, tagline, imageUrl);
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
        String imageUrl = channelMap.get("image").toString();

        return new Channel(channelId, channelName, tagline, imageUrl);
    }

    public Channel registerChannel(Channel channel) {
        printDebug("Attempting to register channel...");
        boolean channelIsNotRegistered = channelRepo.findById(channel.getId()).isEmpty();
        if(channelIsNotRegistered) {
            printDebug("Channel doesn't exist in database.");
            // Channel doesn't exist in database
            printDebug("Saving...");
            Channel savedChannel = channelRepo.save(channel);
            printDebug("New Channel registered in database.");
            return savedChannel;
        }
        printDebug("Channel already registered in database.");
        return null;
    }
}
