package com.grupp4.radioproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "channels")
public class Channel {

    @Id
    /* ANVÄND INTE GENERATED VALUE HÄR. FÖRSTÖR ALLT. */
    @Column(name = "channel_id")
    private long id;

    @Transient
    private String name;

    @OneToMany(mappedBy = "channel")
    private List<Program> programs;

    /** Describes what the channel is about */
    @Transient
    private String tagline;

    public Channel() { }

    public Channel(long id, String channelName) {
        this(id, channelName, "ej tillgängligt");
    }

    public Channel(long id, String name, String tagline) {
        this.id = id;
        this.name = name;
        this.tagline = tagline;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Program> getPrograms() {
        return programs;
    }
    @JsonProperty
    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    @JsonProperty
    public String getTagline() {
        return tagline;
    }
    @JsonIgnore
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", programs=" + programs +
                ", tagline='" + tagline + '\'' +
                '}';
    }
}
