package com.grupp4.radioproject.repositories;

import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.entities.ProgramCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramCategoryRepo extends JpaRepository<ProgramCategory, Long> {
}
