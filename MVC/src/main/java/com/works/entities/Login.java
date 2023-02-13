package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lid;

    @NotEmpty
    @Length(min = 2, max = 100)
    @NotNull
    @Column(length = 100)
    private String name;

    @NotEmpty
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    @NotNull
    @Column(length = 500)
    private String password;


}
