package com.bridgelabz.employeepayrollapp.service;


import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

    @Slf4j
    @Service
    public class EmployeeService {

        private final EmployeeRepository repository;
   @Autowired
      public EmployeeService(EmployeeRepository repository){
       this.repository = repository;
   }

        public Employee addEmployee(Employee employee) {
            return repository.save(employee);
        }

        public List<Employee> getAllEmployees() {
            return repository.findAll();
        }

        public Optional<Employee> getEmployeeById(Long id) {
            return repository.findById(id);
        }

        public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee) {
            return repository.findById(id).map(employee -> {
                employee.setName(updatedEmployee.getName());
                employee.setDepartment(updatedEmployee.getDepartment());
                employee.setSalary(updatedEmployee.getSalary());
                return repository.save(employee);
            });
        }

        public boolean deleteEmployee(Long id) {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return true;
            }
            return false;
        }

        public Employee addEmployee(EmployeeDTO employeeDTO) {
            Employee employee = new Employee(null,employeeDTO.getName(), employeeDTO.getDepartment(), employeeDTO.getSalary());
            return repository.save(employee);
        }

        private final List<Employee> employeeList = new ArrayList<>();
        private long idCounter = 1;
        public Employee addAnEmployee(EmployeeDTO employeeDTO) {
            Employee employee = new Employee(idCounter++, employeeDTO.getName(),employeeDTO.getDepartment(), employeeDTO.getSalary());
            employeeList.add(employee);
            return employee;
        }
        public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
            Optional<Employee> employeeOptional = employeeList.stream()
                    .filter(emp -> emp.getId().equals(id))
                    .findFirst();

            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setName(employeeDTO.getName());
                employee.setSalary(employeeDTO.getSalary());
                return employee;
            }
            return null;
        }
        //UC7
        // Add an employee
        public Employee addemployee(EmployeeDTO employeeDTO) {
            Employee employee = new Employee(idCounter++, employeeDTO.getName(),employeeDTO.getDepartment(), employeeDTO.getSalary());
            employeeList.add(employee);
            log.info("Employee added: {}", employee);
            return employee;
        }
        // Get all employees
        public List<Employee> getAllemployees() {
            log.info("Fetching all employees. Total: {}", employeeList.size());
            return employeeList;
        }

        // Get employee by ID
        public Employee getemployeeById(Long id) {
            log.info("Fetching employee with ID: {}", id);
            return employeeList.stream()
                    .filter(emp -> emp.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        // Update employee
        public Employee updateemployee(Long id, EmployeeDTO employeeDTO) {
            Optional<Employee> employeeOptional = employeeList.stream()
                    .filter(emp -> emp.getId().equals(id))
                    .findFirst();

            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setName(employeeDTO.getName());
                employee.setSalary(employeeDTO.getSalary());
                log.info("Updated employee: {}", employee);
                return employee;
            } else {
                log.warn("Employee with ID {} not found for update.", id);
                return null;
            }
        }

        // Delete employee
        public boolean deleteemployee(Long id) {
            boolean removed = employeeList.removeIf(emp -> emp.getId().equals(id));
            if (removed) {
                log.info("Deleted employee with ID: {}", id);
            } else {
                log.warn("Employee with ID {} not found for deletion.", id);
            }
            return removed;
        }

    }


