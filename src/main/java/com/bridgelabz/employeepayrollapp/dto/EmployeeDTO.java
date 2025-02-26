package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String name;
    private double salary;
    private String department;

    public EmployeeDTO() {}

    public EmployeeDTO(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
    }
}

