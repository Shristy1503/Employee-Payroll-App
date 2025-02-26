package com.bridgelabz.employeepayrollapp.service;


import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

    @Service
    public class EmployeeService {

        private final EmployeeRepository repository;

        // Manual Injection (No @Autowired)
        public EmployeeService(EmployeeRepository repository) {
            this.repository = repository;
        }

        // Convert Model to DTO
        private EmployeeDTO convertToDTO(Employee employee) {
            return new EmployeeDTO(employee.getName(), employee.getSalary());
        }

        // Convert DTO to Model
        private Employee convertToModel(EmployeeDTO employeeDTO) {
            Employee employee = new Employee();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            return employee;
        }

        // GET All Employees
        public List<EmployeeDTO> getAllEmployees() {
            return repository.findAll().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        // GET Employee by ID
        public Optional<EmployeeDTO> getEmployeeById(Long id) {
            return repository.findById(id)
                    .map(this::convertToDTO);
        }

        // POST Create Employee
        public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
            Employee employee = convertToModel(employeeDTO);
            Employee savedEmployee = repository.save(employee);
            return convertToDTO(savedEmployee);
        }

        // PUT Update Employee
        public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
            Employee existingEmployee = repository.findById(id).orElseThrow();
            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setSalary(employeeDTO.getSalary());
            Employee updatedEmployee = repository.save(existingEmployee);
            return convertToDTO(updatedEmployee);
        }

        // DELETE Employee
        public String deleteEmployee(Long id) {
            repository.deleteById(id);
            return "Employee deleted successfully!";
        }
    }


