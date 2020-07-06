package com.rekadanilaci.accenture.dto;

import com.rekadanilaci.accenture.domain.Admin;

public class AdminDataItem {
    private Long id;
    private String name;

    public AdminDataItem(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
    }

}
