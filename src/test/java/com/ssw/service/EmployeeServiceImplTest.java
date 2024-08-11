package com.ssw.service;

import com.ssw.entity.Employee;
import com.ssw.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class EmployeeServiceImplTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    private Employee employee;

    public Employee getEmployee() {

        employee = Employee.builder()
                .employeeName("Suyash")
                .password("pass1234")
                .mail("suyash@example.com")
                .phone(1234567890L)
                .address("123 St")
                .build();
        return employee;
    }

    @Test
    public void testCreateEmployee() {

        // mockito
        Mockito.when(employeeRepository.save(any())).thenReturn(getEmployee());
        Employee createdEmployee = employeeService.createEmployee(getEmployee());
        Assertions.assertNotNull(createdEmployee);

        //         junit
        Assertions.assertEquals("Suyash", createdEmployee.getEmployeeName());

    }

    @Test

    public void testGetAllEmployees() {
        Employee employee2 = Employee.builder()
                .employeeName("Suyash")
                .password("pass5678")
                .mail("suyash@example.com")
                .phone(9876543210L)
                .address("456 St")
                .build();
        List<Employee> employeeList = Arrays.asList(getEmployee(), employee2);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> retrievedEmployees = employeeService.getAllEmployees();
        Assertions.assertEquals(2, retrievedEmployees.size());
    }

    @Test
    public void testFindEmployeeById() {
        int id = 1;
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(getEmployee()));
        Employee foundEmployee = employeeService.findEmployeeById(id);
        Assertions.assertNotNull(foundEmployee);
        Assertions.assertEquals("Suyash", foundEmployee.getEmployeeName());
    }

    @Test
    public void testFindEmployeeByIdNotFound() {
        int id = 1;
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> employeeService.findEmployeeById(id));
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Employee updatedEmployeeData = Employee.builder()
                .employeeName("Suyash")
                .password("newpass1234")
                .mail("Suyash@example.com")
                .phone(1234567891L)
                .address("123 New St")
                .build();
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployeeData);
        Employee updatedEmployee = employeeService.updateEmployee(updatedEmployeeData, id);
        Assertions.assertNotNull(updatedEmployee);
        Assertions.assertEquals("Suyash", updatedEmployee.getEmployeeName());
    }

    @Test
    public void testDeleteEmployee() {
        int id = 1;
        Mockito.when(employeeRepository.existsById(id)).thenReturn(true);
        String result = employeeService.deleteEmployee(id);
        Assertions.assertEquals("Employee deleted successfully", result);
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testDeleteEmployeeNotFound() {
        int id = 1;
        Mockito.when(employeeRepository.existsById(id)).thenReturn(false);
        String result = employeeService.deleteEmployee(id);
        Assertions.assertEquals("Employee not found with id: " + id, result);
    }
}
