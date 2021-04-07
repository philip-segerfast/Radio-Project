package com.grupp4.radioproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "channels")
@JsonIgnoreProperties(value = {"channelName", "tagline"}, allowGetters = true)
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private long id;

    @JsonIgnore
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
        this.id = channelId;
        this.channelName = channelName;
        this.tagline = tagline;
    }

    @JsonProperty
    public String getTagline() {
        return tagline;
    }
    @JsonIgnore
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<Program> getPrograms() {
        return programs;
    }
    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
                "channelId=" + id +
                ", channelName='" + channelName + '\'' +
                ", programs=" + programs +
                '}';
    }
}
