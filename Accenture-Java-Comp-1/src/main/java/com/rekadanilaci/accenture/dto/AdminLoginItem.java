package com.rekadanilaci.accenture.dto;

import com.rekadanilaci.accenture.domain.Admin;

public class AdminLoginItem {
    private Long id;
    private String password;

    public AdminLoginItem(Admin admin) {
        this.id = admin.getId();
        this.password = admin.getPassword();
    }

    public AdminLoginItem() {
    }

    public Long getId() {
        return id;
    }
}
