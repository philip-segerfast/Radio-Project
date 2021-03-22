package com.grupp4.radioproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@JsonIgnoreProperties(value = {"episodeId", "title", "description"}, allowGetters = true)
public class Schedule {

    @Id
    private long episodeId;
    private String title;
    private String description;

    @ManyToOne
    Channel channel;

    @ManyToOne
    Program program;

    public Schedule() {
    }

    public Schedule(String title, String description, Channel channel, Program program) {
        this.title = title;
        this.description = description;
        this.channel = channel;
        this.program = program;
    }

    public Schedule(long episodeId, String title, String description) {
        this.episodeId = episodeId;
        this.title = title;
        this.description = description;
    }

    public Schedule(long episodeId, String title, String description, Channel channel, Program program) {
        this.episodeId = episodeId;
        this.title = title;
        this.description = description;
        this.channel = channel;
        this.program = program;
    }

    public long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(long episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "episodeId=" + episodeId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", channel=" + channel +
                ", program=" + program +
                '}';
    }
}
