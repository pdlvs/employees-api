package com.api.employee.dto;

import javax.validation.constraints.NotBlank;

public class EmployeeDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
