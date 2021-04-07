package com.grupp4.radioproject.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long programId;

    private String name;

    @ManyToOne
    private Channel channel;

    @JsonIgnore
    private String description;

    @ManyToOne
    private ProgramCategory programCategory;

    public Program(long programId, String name) {
        this.programId = programId;
        this.name = name;
    }

    public Program(long id, String name, Channel channel, String description) {
        this.programId = id;
        this.name = name;
        this.channel = channel;
        this.description = description;
    }

    public Program(long programId, String name, Channel channel, String description, ProgramCategory programCategory) {
        this.programId = programId;
        this.name = name;
        this.channel = channel;
        this.description = description;
        this.programCategory = programCategory;
    }

    public Program() { }

    public long getProgramId() {
        return programId;
    }
    @JsonProperty
    public void setProgramId(long programId) {
        this.programId = programId;
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
    @JsonIgnore
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
    @JsonIgnore
    public void setProgramCategory(ProgramCategory programCategory) {
        this.programCategory = programCategory;
    }


    @Override
    public String toString() {
        return "Program{" +
                "programId=" + programId +
                ", name='" + name + '\'' +
                ", channel=" + channel +
                ", description='" + description + '\'' +
                ", programCategory=" + programCategory +
                '}';
    }
}
