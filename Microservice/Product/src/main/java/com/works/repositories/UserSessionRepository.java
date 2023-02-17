package com.works.repositories;

import com.works.entities.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSession, String> {
}
