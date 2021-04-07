package com.grupp4.radioproject.repositories;

import com.grupp4.radioproject.entities.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO channel(channel_id) VALUES (:channel_id)", nativeQuery = true)
    void customSave(@Param("channel_id") long channelId);

}
