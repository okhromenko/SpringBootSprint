package edu.sprint.springbootsprint.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Employee {
    private final int id;
    private final int employeeNumber;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate dateOfBirth;
    private LocalDate dateOfEmployment;
    private String email;
    private String phoneNumber;
    private String address;
    private int status;
}
