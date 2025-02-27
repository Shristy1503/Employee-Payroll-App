package com.bridgelabz.employeepayrollapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.DataAmount;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


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
   // @NotBlank(message = "Department is required")
    //private String department;
    @Min(value = 10000, message = "Salary must be at least 10,000")
    private double salary;
    private String gender;

    private LocalDate startDate;

    private String note;
    private String profilePic;

    @ElementCollection
    private List<String> department;


    public Employee( @NotEmpty(message = "Name cannot be empty.") @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Name must start with a capital letter and have at least 3 characters.") String name, @NotEmpty(message = "Department cannot be empty") List<String> department, double salary) {
    }

    public Employee(long l, @NotEmpty(message = "Name cannot be empty.") @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Name must start with a capital letter and have at least 3 characters.") String name, @NotEmpty(message = "Department cannot be empty") List<String> department, double salary) {
    }
}
