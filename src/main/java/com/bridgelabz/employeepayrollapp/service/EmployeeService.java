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
    }


