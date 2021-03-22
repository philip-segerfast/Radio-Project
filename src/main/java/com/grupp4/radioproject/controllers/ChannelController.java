package com.grupp4.radioproject.controllers;

import com.grupp4.radioproject.entities.Channel;
import com.grupp4.radioproject.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping("/rest/channels")
    public List<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }



}
