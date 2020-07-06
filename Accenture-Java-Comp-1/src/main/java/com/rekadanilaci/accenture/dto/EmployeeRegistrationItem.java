package com.rekadanilaci.accenture.dto;

public class EmployeeRegistrationItem {
    private String name;
    private String password;
    private Long adminId;

    public EmployeeRegistrationItem(String name, String password, Long adminId) {
        this.name = name;
        this.password = password;
        this.adminId = adminId;
    }

    public EmployeeRegistrationItem() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Long getAdminId() {
        return adminId;
    }
}
