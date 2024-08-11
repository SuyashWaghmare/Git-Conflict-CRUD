package com.ssw.serviceimpl;


import com.ssw.entity.Employee;
import com.ssw.repository.EmployeeRepository;
import com.ssw.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeServiceimpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee createEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }



    public Employee findEmployeeById(int id) {


        return employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {

        Employee existing = employeeRepository.findById(id).get();

        if (existing != null) {

            existing.setEmployeeName(employee.getEmployeeName());
            existing.setPhone(employee.getPhone());
            existing.setMail(employee.getMail());
            existing.setPassword(employee.getPassword());


            return employeeRepository.save(existing);
        } else {
            return new Employee();
        }


    }

    @Override
    public String deleteEmployee(int id) {

        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        } else {
            return "Employee not found with id: " + id;
        }
    }
}



