package com.grupp4.radioproject.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @Column(name = "program_id")
    private long id;

    @Transient
    private String name;

    @ManyToOne(optional = true)
    @JoinColumn(name = "channel_id")
    @JsonIgnoreProperties({"programs", "tagline"})
    private Channel channel;

    @Transient
    private String description;

    @ManyToOne(optional = true)
    @JoinColumn(name = "category_id")
    private ProgramCategory programCategory;

    public Program(long id, String name) {
        this(id, name, null, "Unavailable");
    }

    public Program(long id, String name, Channel channel, String description) {
        this(id, name, channel, description, null);
    }

    public Program(long id, String name, Channel channel, String description, ProgramCategory programCategory) {
        this.id = id;
        this.name = name;
        this.channel = channel;
        this.description = description;
        this.programCategory = programCategory;
    }

    public Program() { }

    @JsonProperty
    public long getId() {
        return id;
    }
    @JsonProperty
    public void setId(long programId) {
        this.id = programId;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
    @JsonIgnore
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public Channel getChannel() {
        return channel;
    }
    @JsonProperty
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }
    @JsonIgnore
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public ProgramCategory getProgramCategory() {
        return programCategory;
    }
    @JsonProperty
    public void setProgramCategory(ProgramCategory programCategory) {
        this.programCategory = programCategory;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId=" + id +
                ", name='" + name + '\'' +
                ", channel=" + channel +
                ", description='" + description + '\'' +
                ", programCategory=" + programCategory +
                '}';
    }
}
