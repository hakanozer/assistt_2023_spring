package com.works.repositories;

import com.works.entities.DBSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DBSessionRepository extends JpaRepository<DBSession, Long> {

    Optional<DBSession> findBySessionIDEqualsAndStatusEquals(String sessionID, Boolean status);
    boolean existsBySessionID(String sessionID);

}