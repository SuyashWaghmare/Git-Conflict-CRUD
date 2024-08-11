package com.ssw.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "Employee_Table")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee_ID")
    private int id;

    @NotEmpty(message = "Username is required")
    @Size(min = 3, max = 6, message = "Username must be between 3 and 6 characters")
    @Column(name = "Employee_Name")
    private String employeeName;

    @NotEmpty(message = "Password is required")
    @Size(min = 4, message = "Password must be at least 4 characters")
    @Column(name = "Password")
    private String password;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "Mail")
    private String mail;

    @NotNull(message = "Phone number is required")
    @Column(name = "Phone")
    private long phone;

    @NotEmpty(message = "Address is required")
    @Size(max = 10, message = "Address must be less than 10 characters")
    @Column(name = "Address")
    private String address;





}
