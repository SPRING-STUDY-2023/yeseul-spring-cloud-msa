package com.example.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TestMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
