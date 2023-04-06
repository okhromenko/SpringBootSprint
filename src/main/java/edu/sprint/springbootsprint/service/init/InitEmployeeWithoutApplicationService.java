package edu.sprint.springbootsprint.service.init;

import edu.sprint.springbootsprint.model.Employee;
import edu.sprint.springbootsprint.service.EmployeeService;
import edu.sprint.springbootsprint.service.create.CreateEmployee;

import java.util.List;

public class InitEmployeeWithoutApplicationService {
    public static final int COUNT_INIT_EMPLOYEE = 2;

    public static List<Employee> init() {

        for (int i = 0; i < COUNT_INIT_EMPLOYEE; i++) {
            EmployeeService.addEmployee(CreateEmployee.createEmployee());
        }

        return EmployeeService.employeeDatabase;
    }
}
