package com.grupp4.radioproject.repositories;

import com.grupp4.radioproject.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProgramRepo extends JpaRepository<Program, Long> {

    /*
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO programs(program_id, channel_id) VALUES (:program_id, :channel_id)", nativeQuery = true)
    void customSave(@Param("program_id") long programId, @Param("channel_id") long channelId);
    */

}
