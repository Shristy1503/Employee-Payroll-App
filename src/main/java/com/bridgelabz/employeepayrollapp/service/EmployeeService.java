package com.bridgelabz.employeepayrollapp.service;


import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

    @Service
    public class EmployeeService {
   @Autowired
        private final EmployeeRepository repository;
        public List<Employee> getAllEmployees() {
            return repository.findAll();
        }

        // Manual Injection (No @Autowired)
        public EmployeeService(EmployeeRepository repository) {
            this.repository = repository;
        }

        public Employee getEmployeeById(Long id) {
            return repository.findById(id).orElse(null);
        }

        public Employee createEmployee(Employee employee) {
            return repository.save(employee);
        }

        public Employee updateEmployee(Long id, Employee employeeDetails) {
            Employee employee = repository.findById(id).orElse(null);
            if (employee != null) {
                employee.setName(employeeDetails.getName());
                employee.setSalary(employeeDetails.getSalary());
                employee.setDepartment(employeeDetails.getDepartment());
                return repository.save(employee);
            }
            return null;
        }

        public void deleteEmployee(Long id) {
            repository.deleteById(id);
        }
    }


