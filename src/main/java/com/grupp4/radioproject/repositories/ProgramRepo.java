package com.grupp4.radioproject.repositories;

import com.grupp4.radioproject.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepo extends JpaRepository<Program, Long> {
    Program findByProgram(String program_id);
}
