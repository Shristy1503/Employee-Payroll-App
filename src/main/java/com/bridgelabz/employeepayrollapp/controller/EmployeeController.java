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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
  //get all employees
   @GetMapping
   public ResponseEntity<List<Employee>> getAllEmployees() {
       return ResponseEntity.ok(employeeService.getAllEmployees());
   }
  //get employees by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id) ?
                ResponseEntity.ok("deleted employee"):
                ResponseEntity.notFound().build();
    }

    @PostMapping("/log")
    public ResponseEntity<Employee> addAnEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Received request to add employee: {}", employeeDTO);
        Employee newEmployee = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(newEmployee);
    }

    // Get All Employees
    @GetMapping("/log")
    public ResponseEntity<List<Employee>> getAllemployees() {
        log.info("Received request to fetch all employees.");
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Get Employee by ID
    @GetMapping("/{id}/log")
    public ResponseEntity<Optional> getAnEmployeeById(@PathVariable Long id) {
        log.info("Received request to fetch employee with ID: {}", id);
        Optional employee = employeeService.getEmployeeById(id);
        return (employee != null) ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    // Update Employee
    @PutMapping("/{id}/log")
    public ResponseEntity<Employee> updateAnEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        log.info("Received request to update employee with ID: {}", id);
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDTO );
        return (updatedEmployee != null) ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    // Delete Employee
    @DeleteMapping("/{id}/log")
    public ResponseEntity<Void> deleteAnEmployee(@PathVariable Long id) {
        log.info("Received request to delete employee with ID: {}", id);
        return employeeService.deleteEmployee(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

   

