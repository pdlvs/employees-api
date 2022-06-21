package com.api.employee.controller;

import com.api.employee.dto.EmployeeDTO;
import com.api.employee.entity.Employee;
import com.api.employee.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/employees")
class EmployeeController {
    final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Object> newEmployee(@RequestBody @Valid EmployeeDTO newEmployee) {
        var employeeEntity = new Employee();
        BeanUtils.copyProperties(newEmployee, employeeEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employeeEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {

        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (!employeeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(employeeOptional.get());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> replaceEmployee(@RequestBody EmployeeDTO newEmployee, @PathVariable Long id) {

        Optional<Employee> employeeOptional = employeeService.findById(id);
        if(!employeeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        var employeeEntity = new Employee();
        BeanUtils.copyProperties(newEmployee, employeeEntity);
        employeeEntity.setId(employeeOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employeeEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (!employeeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found. ");
        }
        employeeService.delete(employeeOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
    }

}
