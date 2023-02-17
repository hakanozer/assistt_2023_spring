package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Email
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;

    @NotEmpty
    @NotNull
    private String password;

}
