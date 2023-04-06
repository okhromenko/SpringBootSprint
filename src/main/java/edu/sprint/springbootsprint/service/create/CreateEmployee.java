package edu.sprint.springbootsprint.service.create;

import edu.sprint.springbootsprint.model.Employee;
import edu.sprint.springbootsprint.service.EmployeeService;

import java.time.LocalDate;

public class CreateEmployee {
    private static int count;
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String PATRONYMIC = "Patronymic";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "9811111111";
    private static final String ADDRESS = "Владивосток";
    private static final LocalDate EMPLOYEE_DATE = LocalDate.of(2023, 4, 6);

    public static Employee createEmployee(){

        if (EmployeeService.employeeDatabase.isEmpty())
            count = 0;

        count++;

        return new Employee(
                count,
                count,
                String.format("%s_%d", NAME, count),
                String.format("%s_%d", SURNAME, count),
                PATRONYMIC,
                EMPLOYEE_DATE,
                EMPLOYEE_DATE,
                EMAIL,
                PHONE_NUMBER,
                ADDRESS,
                count
        );
    }
}
