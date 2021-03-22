package com.grupp4.radioproject.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "programs")
@JsonIgnoreProperties(value = {"name", "channel", "description"}, allowGetters = true)
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long programId;

    private String name;




    @ManyToOne
    private Channel channel;

    private String description;

    @ManyToOne
    private ProgramCategory programCategory;

    private Program() {}

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

    public long getProgramId() {
        return programId;
    }
    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Channel getChannel() {
        return channel;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public ProgramCategory getProgramCategory() {
        return programCategory;
    }
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
