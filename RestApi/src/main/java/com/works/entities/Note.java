package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Note extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;

    @NotEmpty
    @Length(min = 3, max = 100)
    @NotNull
    @Column(length = 100)
    private String title;

    @NotEmpty
    @Length(min = 2, max = 200)
    @NotNull
    @Column(length = 200)
    private String detail;


    @PostPersist
    public void postPersist() {
        System.out.println("postPersist");
    }

    @PrePersist
    public void prePersist() {
        System.out.println("prePersist");
    }
}
