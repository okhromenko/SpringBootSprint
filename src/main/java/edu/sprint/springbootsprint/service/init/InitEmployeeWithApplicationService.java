package edu.sprint.springbootsprint.service.init;

import edu.sprint.springbootsprint.model.Application;
import edu.sprint.springbootsprint.model.Employee;
import edu.sprint.springbootsprint.service.ApplicationService;
import edu.sprint.springbootsprint.service.EmployeeService;
import edu.sprint.springbootsprint.service.create.CreateApplication;
import edu.sprint.springbootsprint.service.create.CreateEmployee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitEmployeeWithApplicationService {
    public static final int COUNT_INIT_EMPLOYEE = 1;
    public static final int COUNT_INIT_APPLICATION_FOR_EMPLOYEE = 2;

    public static Map<Employee, List<Application>> init() {
        ApplicationService.applicationDatabase.clear();
        EmployeeService.employeeDatabase.clear();

        Map<Employee, List<Application>> map = new HashMap<>();
        List<Application> applicationList = new ArrayList<>();

        for (int i = 1; i <= COUNT_INIT_EMPLOYEE; i++) {
            Employee employee = CreateEmployee.createEmployee();

            EmployeeService.addEmployee(employee);

            for (int j = 1; j <= COUNT_INIT_APPLICATION_FOR_EMPLOYEE; j++) {
                Application application = CreateApplication.createApplication(i);

                ApplicationService.addApplication(application);
                applicationList.add(application);
            }

            map.put(employee, new ArrayList<>(applicationList));
            applicationList.clear();
        }

        return map;
    }
}
