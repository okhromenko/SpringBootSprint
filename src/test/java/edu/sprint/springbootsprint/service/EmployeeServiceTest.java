package edu.sprint.springbootsprint.service;

import edu.sprint.springbootsprint.exception.employee.EmployeeAlreadyExistException;
import edu.sprint.springbootsprint.exception.employee.EmployeeNotFoundException;
import edu.sprint.springbootsprint.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private static final int ID = 1;
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String PATRONYMIC = "Patronymic";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(2000, 12, 31);
    private static final LocalDate DATE_OF_EMPLOYMENT = LocalDate.of(2023, 3, 30);
    private static final int EMPLOYEE_NUMBER = 1;
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "9811111111";
    private static final String ADDRESS = "Владивосток";
    private static final int STATUS = 1;

    @BeforeEach
    @AfterEach
    void checkDatabaseEmpty() {
        assertTrue(EmployeeService.employeeDatabase.isEmpty());
    }

    @Test
    void shouldAddEmployee() {
        Employee employee = new Employee(
                ID,
                EMPLOYEE_NUMBER,
                NAME,
                SURNAME,
                PATRONYMIC,
                DATE_OF_BIRTH,
                DATE_OF_EMPLOYMENT,
                EMAIL,
                PHONE_NUMBER,
                ADDRESS,
                STATUS
        );

        EmployeeService.addEmployee(employee);

        assertEquals(1, EmployeeService.employeeDatabase.size());

        Employee currentEmployee = EmployeeService.employeeDatabase.get(0);

        assertEquals(NAME, currentEmployee.getName());
        assertEquals(SURNAME, currentEmployee.getSurname());
        assertEquals(PATRONYMIC, currentEmployee.getPatronymic());
        assertEquals(DATE_OF_BIRTH, currentEmployee.getDateOfBirth());
        assertEquals(DATE_OF_EMPLOYMENT, currentEmployee.getDateOfEmployment());
        assertEquals(EMPLOYEE_NUMBER, currentEmployee.getEmployeeNumber());
        assertEquals(EMAIL, currentEmployee.getEmail());
        assertEquals(PHONE_NUMBER, currentEmployee.getPhoneNumber());
        assertEquals(ADDRESS, currentEmployee.getAddress());
        assertEquals(STATUS, currentEmployee.getStatus());

        EmployeeService.employeeDatabase.remove(0);
    }

    @Test
    void shouldReturnEmployee() {
        Employee employee = new Employee(
                ID,
                EMPLOYEE_NUMBER,
                NAME,
                SURNAME,
                PATRONYMIC,
                DATE_OF_BIRTH,
                DATE_OF_EMPLOYMENT,
                EMAIL,
                PHONE_NUMBER,
                ADDRESS,
                STATUS
        );

        EmployeeService.employeeDatabase.add(employee);

        Employee currentEmployee = EmployeeService.getEmployee(EMPLOYEE_NUMBER);

        assertNotNull(currentEmployee);

        assertEquals(NAME, currentEmployee.getName());
        assertEquals(SURNAME, currentEmployee.getSurname());
        assertEquals(PATRONYMIC, currentEmployee.getPatronymic());
        assertEquals(DATE_OF_BIRTH, currentEmployee.getDateOfBirth());
        assertEquals(DATE_OF_EMPLOYMENT, currentEmployee.getDateOfEmployment());
        assertEquals(EMPLOYEE_NUMBER, currentEmployee.getEmployeeNumber());
        assertEquals(EMAIL, currentEmployee.getEmail());
        assertEquals(PHONE_NUMBER, currentEmployee.getPhoneNumber());
        assertEquals(ADDRESS, currentEmployee.getAddress());
        assertEquals(STATUS, currentEmployee.getStatus());

        EmployeeService.employeeDatabase.remove(0);
    }

    @Test
    void shouldDeleteEmployee() {
        Employee employee = new Employee(
                ID,
                EMPLOYEE_NUMBER,
                NAME,
                SURNAME,
                PATRONYMIC,
                DATE_OF_BIRTH,
                DATE_OF_EMPLOYMENT,
                EMAIL,
                PHONE_NUMBER,
                ADDRESS,
                STATUS
        );

        EmployeeService.employeeDatabase.add(employee);

        EmployeeService.deleteEmployee(EMPLOYEE_NUMBER);
    }

    @Test
    void shouldUpdateEmployee() {
        Employee employee = new Employee(
                ID,
                EMPLOYEE_NUMBER,
                NAME,
                SURNAME,
                PATRONYMIC,
                DATE_OF_BIRTH,
                DATE_OF_EMPLOYMENT,
                EMAIL,
                PHONE_NUMBER,
                ADDRESS,
                STATUS
        );

        EmployeeService.employeeDatabase.add(employee);

        String updateName = "new name";

        Employee updateEmployee = new Employee(
                ID,
                EMPLOYEE_NUMBER,
                updateName,
                SURNAME,
                PATRONYMIC,
                DATE_OF_BIRTH,
                DATE_OF_EMPLOYMENT,
                EMAIL,
                PHONE_NUMBER,
                ADDRESS,
                STATUS
        );

        EmployeeService.updateEmployee(updateEmployee);

        Employee employeeFromDB = EmployeeService.employeeDatabase.get(0);

        assertEquals(updateName, employeeFromDB.getName());

        EmployeeService.employeeDatabase.remove(0);
    }

    @Test
    void shouldThrowEmployeeAlreadyExistException() {
        Employee employee = new Employee(
                ID,
                EMPLOYEE_NUMBER,
                NAME,
                SURNAME,
                PATRONYMIC,
                DATE_OF_BIRTH,
                DATE_OF_EMPLOYMENT,
                EMAIL,
                PHONE_NUMBER,
                ADDRESS,
                STATUS
        );

        EmployeeService.employeeDatabase.add(employee);

        assertThrows(EmployeeAlreadyExistException.class,
                () -> EmployeeService.addEmployee(employee),
                "shouldThrowEmployeeAlreadyExistException");

        EmployeeService.employeeDatabase.remove(0);
    }

    @Test
    void shouldThrowEmployeeNotFoundException() {
        Employee employee = new Employee(
                ID,
                EMPLOYEE_NUMBER,
                NAME,
                SURNAME,
                PATRONYMIC,
                DATE_OF_BIRTH,
                DATE_OF_EMPLOYMENT,
                EMAIL,
                PHONE_NUMBER,
                ADDRESS,
                STATUS
        );

        EmployeeService.employeeDatabase.add(employee);

        int newId = 2;

        assertThrows(EmployeeNotFoundException.class,
                () -> EmployeeService.deleteEmployee(newId),
                "shouldThrowEmployeeNotFoundException");

        EmployeeService.employeeDatabase.remove(0);
    }
}