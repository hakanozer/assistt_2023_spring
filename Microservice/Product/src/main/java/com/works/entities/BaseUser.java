package com.works.entities;

import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseUser {

    @Id
    private String id;
    private String username;
    private Date loginTime;
    private String browser;

}
