package com.grupp4.radioproject.repositories;

import com.grupp4.radioproject.entities.Program;
import com.grupp4.radioproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO program_favourites (user_id, program_id) values (:user_id, :program_id)", nativeQuery = true)
    void saveFavouriteProgram(@Param("user_id") long userId, @Param("program_id") long programId);
}
