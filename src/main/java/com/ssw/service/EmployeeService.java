package com.ssw.service;


import com.ssw.entity.Employee;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee findEmployeeById(int id);

    Employee updateEmployee(Employee employee, int id);

    String deleteEmployee(int id);
}
