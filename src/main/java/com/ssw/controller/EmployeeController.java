package com.ssw.controller;

import com.ssw.entity.Employee;
import com.ssw.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/createEmployee")
    ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {

        return new ResponseEntity<Employee>( employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/getAllEmployees")
    ResponseEntity<List<Employee>> getAllEmployees() {


        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.FOUND);


    }

    @GetMapping("/findEmployeeById/{id}")
    ResponseEntity<Employee> findEmployeeById(@PathVariable int id) {

        return new ResponseEntity<Employee>(employeeService.findEmployeeById(id), HttpStatus.FOUND);


    }

      @PutMapping("/updateEmployee/{id}")
      ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable int id) {

        return new ResponseEntity<>(employeeService.updateEmployee(employee,id),HttpStatus.ACCEPTED);
      }

      @DeleteMapping("/deleteEmployee/{id}")
      ResponseEntity<String> deleteEmployee(@PathVariable int id) {


        return new ResponseEntity<String>(employeeService.deleteEmployee(id),HttpStatus.OK);
      }


}

