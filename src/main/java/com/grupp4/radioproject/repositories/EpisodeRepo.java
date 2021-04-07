package com.grupp4.radioproject.repositories;

import com.grupp4.radioproject.entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepo extends JpaRepository<Episode, Long> {
}
