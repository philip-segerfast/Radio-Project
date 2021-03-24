package com.grupp4.radioproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Not stored our database.
 */
@Entity
@Table(name = "program_categories")
@JsonIgnoreProperties(value = {"id", "name"}, allowGetters = true)
public class ProgramCategory {

    @Id
    private long id;
    private String name;

    public ProgramCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
