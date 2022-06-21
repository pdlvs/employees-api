package com.api.employee.services;

import com.api.employee.entity.Employee;
import com.api.employee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Transactional
    public Employee save(Employee employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public void delete(Employee employeeEntity) {
        employeeRepository.delete(employeeEntity);
    }
}
