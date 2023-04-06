package edu.sprint.springbootsprint.service;

import edu.sprint.springbootsprint.exception.application.ApplicationDismissedEmployeeException;
import edu.sprint.springbootsprint.exception.application.ApplicationExceededLimitException;
import edu.sprint.springbootsprint.exception.application.ApplicationNotFoundException;
import edu.sprint.springbootsprint.exception.employee.EmployeeNotFoundException;
import edu.sprint.springbootsprint.model.Application;
import edu.sprint.springbootsprint.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationService {
    public static final int MAX_ORDER = 50;
    private static final int STATUS_EMPLOYEE_DISMISS = 0;

    public static List<Application> applicationDatabase = new ArrayList<>();

    private static boolean checkStatusEmployeeDismiss(int employeeId) throws EmployeeNotFoundException {
        Employee employee = EmployeeService.getEmployee(employeeId);

        if (employee == null) {
            throw new EmployeeNotFoundException("The employee does not exist in the database");
        }

        return employee.getStatus() == STATUS_EMPLOYEE_DISMISS;
    }

    public static List<Application> addApplication(Application application)
            throws ApplicationDismissedEmployeeException, ApplicationExceededLimitException {

        long countApplication = applicationDatabase.stream()
                .filter(s -> s.getEmployeeId() == application.getEmployeeId())
                .count();

        if (countApplication == MAX_ORDER) {
            throw new ApplicationExceededLimitException("The maximum number of applications has been exceeded");
        }

        if (checkStatusEmployeeDismiss(application.getEmployeeId())) {
            throw new ApplicationDismissedEmployeeException("This employee was dismissed");
        }

        applicationDatabase.add(application);

        return applicationDatabase;
    }

    public static Application getApplication(int id) {
        return applicationDatabase.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<Application> getApplicationForEmployee(int employeeId) {
        return applicationDatabase.stream()
                .filter(s -> s.getEmployeeId() == employeeId)
                .collect(Collectors.toList());
    }

    public static List<Application> deleteApplication(int id) throws ApplicationNotFoundException {
        Application application = getApplication(id);

        if (application != null) {
            applicationDatabase.remove(application);

            return applicationDatabase;
        }

        throw new ApplicationNotFoundException("The application does not exist in the database");
    }

    public static List<Application> updateApplication(int id, int categoryId)
            throws ApplicationNotFoundException {
        Application currentApplication = getApplication(id);

        if (currentApplication != null) {
            currentApplication.setCategoryId(categoryId);

            return applicationDatabase;
        }

        throw new ApplicationNotFoundException("The application does not exist in the database");
    }

    public static List<Application> updateAppApplication(Application application)
            throws ApplicationNotFoundException {
        Application currentApplication = getApplication(application.getId());

        if (currentApplication != null) {
            applicationDatabase.set(applicationDatabase.indexOf(currentApplication), application);

            return applicationDatabase;
        }

        throw new ApplicationNotFoundException("The application does not exist in the database");
    }
}
