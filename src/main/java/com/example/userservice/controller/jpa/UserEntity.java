package com.example.userservice.controller.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="test_users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 50, unique = true)
    private String userId;
    @Column(nullable = false, length = 50)
    private String pwd;
    @Column(nullable = false)
    private String encryptPwd;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 100)
    private String email;
}
