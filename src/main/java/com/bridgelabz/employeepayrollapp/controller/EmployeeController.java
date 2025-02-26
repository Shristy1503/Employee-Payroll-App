package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import com.bridgelabz.employeepayrollapp.service.EmployeeService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    /*
    @Autowired
    private EmployeeRepository repository;


    // Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Get Employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Add New Employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    // Update Employee by ID
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return repository.save(employee);
    }

    //delete
    // Delete Employee by ID
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);

    }  */

    // Get all emoloyees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // get employee by ID
    @GetMapping("/{id}")
    public Optional<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // create new employee
    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    //  Update existing employee
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }
    // delete employee by ID
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
}

   

