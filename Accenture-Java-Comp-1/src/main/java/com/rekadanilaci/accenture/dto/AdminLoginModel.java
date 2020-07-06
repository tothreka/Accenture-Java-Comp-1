package com.rekadanilaci.accenture.dto;

public class AdminLoginModel {
    private Long id;
    private String password;

    public AdminLoginModel(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public AdminLoginModel() {
    }

    public Long getId() {
        return id;
    }
}
