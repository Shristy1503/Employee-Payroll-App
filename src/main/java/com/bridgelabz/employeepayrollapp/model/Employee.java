package com.bridgelabz.employeepayrollapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.DataAmount;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Department is required")
    private String department;
    @Min(value = 10000, message = "Salary must be at least 10,000")
    private double salary;

}
