package com.bridgelabz.employeepayrollapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeDTO {
    @NotEmpty(message = "Name cannot be empty.")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Name must start with a capital letter and have at least 3 characters.")
    private String name;
    private double salary;
    private String department;

    public EmployeeDTO() {}

    public EmployeeDTO(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
}

