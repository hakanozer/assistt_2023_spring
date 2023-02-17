package com.works.services;

import com.works.entities.UserSession;
import com.works.repositories.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    final UserSessionRepository uRepo;

    public Iterable<UserSession> list() {
       return uRepo.findAll();
    }

}
