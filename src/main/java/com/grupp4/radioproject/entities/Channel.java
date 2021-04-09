package com.grupp4.radioproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "channels")
@JsonIgnoreProperties(value = {"channelName", "tagline"}, allowGetters = true)
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long channelId;

    private String channelName;

    @JsonIgnore
    @OneToMany(mappedBy = "channel")
    private List<Program> programs;

    /**
     * Describes what the channel is about
     */
    private String tagline;

    public Channel() { }

    public Channel(long channelId, String channelName) {
        this(channelId, channelName, "icke-definierad");
    }

    public Channel(long channelId, String channelName, String tagline) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.tagline = tagline;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelId=" + channelId +
                ", channelName='" + channelName + '\'' +
                ", programs=" + programs +
                '}';
    }
}
