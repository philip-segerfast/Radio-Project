package com.grupp4.radioproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "programs")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long programId;

    private Program() {}
}
