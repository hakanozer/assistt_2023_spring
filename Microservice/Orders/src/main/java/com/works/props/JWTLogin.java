package com.works.props;

@lombok.Data
public class JWTLogin {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;
    private String token;
}