package com.grupp4.radioproject.repositories;

import com.grupp4.radioproject.entities.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {
    Channel findByChannel(String channel);
}
