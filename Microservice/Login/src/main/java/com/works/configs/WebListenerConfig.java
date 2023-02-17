package com.works.configs;

import com.works.entities.DBSession;
import com.works.repositories.DBSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class WebListenerConfig implements HttpSessionListener {

    final DBSessionRepository dbSessionRepository;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println( "sessionCreated " +  se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sessionID = se.getSession().getId();
        Optional<DBSession> optionalDBSession = dbSessionRepository.findBySessionIDEqualsAndStatusEquals(sessionID, true);
        if (optionalDBSession.isPresent() ) {
            DBSession dbSession = optionalDBSession.get();
            dbSessionRepository.delete(dbSession);
        }
    }
}
