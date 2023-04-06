package edu.sprint.springbootsprint.service;

import edu.sprint.springbootsprint.exception.application.ApplicationDismissedEmployeeException;
import edu.sprint.springbootsprint.exception.application.ApplicationExceededLimitException;
import edu.sprint.springbootsprint.exception.application.ApplicationNotFoundException;
import edu.sprint.springbootsprint.model.Application;
import edu.sprint.springbootsprint.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationServiceTest {
    private static final int ID = 1;
    private static final int EMPLOYEE_ID = 1;
    private static final int TYPE_OF_APPLICATION_ID = 1;
    private static final int TYPE_OF_RECEIPT_ID = 1;
    private static final int CATEGORY_ID = 1;
    private static final int STATUS_ID = 1;
    private static final LocalDate DATE_OF_CREATION = LocalDate.of(2023, 3, 30);
    private static final LocalDate DATE_OF_CHANGE = LocalDate.of(2023, 3, 30);

    @BeforeEach
    @AfterEach
    void checkDatabaseEmpty() {
        assertTrue(ApplicationService.applicationDatabase.isEmpty());
    }

    @Test
    void shouldAddApplication() {
        Employee employee = new Employee(
                ID,
                EMPLOYEE_ID,
                "NAME",
                "SURNAME",
                "PATRONYMIC",
                LocalDate.of(2000, 12, 31),
                LocalDate.of(2023, 4, 6),
                "EMAIL",
                "PHONE_NUMBER",
                "ADDRESS",
                1
        );

        EmployeeService.employeeDatabase.add(employee);

        Application application = new Application(
                ID,
                EMPLOYEE_ID,
                TYPE_OF_APPLICATION_ID,
                TYPE_OF_RECEIPT_ID,
                CATEGORY_ID,
                STATUS_ID,
                DATE_OF_CREATION,
                DATE_OF_CHANGE
        );

        ApplicationService.addApplication(application);

        assertEquals(1, ApplicationService.applicationDatabase.size());

        Application currentApplication = ApplicationService.applicationDatabase.get(0);

        assertEquals(ID, currentApplication.getId());
        assertEquals(EMPLOYEE_ID, currentApplication.getEmployeeId());
        assertEquals(TYPE_OF_APPLICATION_ID, currentApplication.getTypeOfApplicationId());
        assertEquals(TYPE_OF_RECEIPT_ID, currentApplication.getTypeOfReceiptId());
        assertEquals(CATEGORY_ID, currentApplication.getCategoryId());
        assertEquals(STATUS_ID, currentApplication.getStatusId());
        assertEquals(DATE_OF_CREATION, currentApplication.getDateOfCreation());
        assertEquals(DATE_OF_CHANGE, currentApplication.getDateOfChange());

        ApplicationService.applicationDatabase.remove(0);
        EmployeeService.employeeDatabase.remove(0);
    }

    @Test
    void shouldReturnApplication() {
        Application application = new Application(
                ID,
                EMPLOYEE_ID,
                TYPE_OF_APPLICATION_ID,
                TYPE_OF_RECEIPT_ID,
                CATEGORY_ID,
                STATUS_ID,
                DATE_OF_CREATION,
                DATE_OF_CHANGE
        );

        ApplicationService.applicationDatabase.add(application);

        Application currentApplication = ApplicationService.applicationDatabase.get(0);

        assertNotNull(currentApplication);

        assertEquals(ID, currentApplication.getId());
        assertEquals(EMPLOYEE_ID, currentApplication.getEmployeeId());
        assertEquals(TYPE_OF_APPLICATION_ID, currentApplication.getTypeOfApplicationId());
        assertEquals(TYPE_OF_RECEIPT_ID, currentApplication.getTypeOfReceiptId());
        assertEquals(CATEGORY_ID, currentApplication.getCategoryId());
        assertEquals(STATUS_ID, currentApplication.getStatusId());
        assertEquals(DATE_OF_CREATION, currentApplication.getDateOfCreation());
        assertEquals(DATE_OF_CHANGE, currentApplication.getDateOfChange());

        ApplicationService.applicationDatabase.remove(0);
    }

    @Test
    void shouldDeleteApplication() {
        Application application = new Application(
                ID,
                EMPLOYEE_ID,
                TYPE_OF_APPLICATION_ID,
                TYPE_OF_RECEIPT_ID,
                CATEGORY_ID,
                STATUS_ID,
                DATE_OF_CREATION,
                DATE_OF_CHANGE
        );

        ApplicationService.applicationDatabase.add(application);

        ApplicationService.deleteApplication(ID);
    }

    @Test
    void shouldUpdateApplication() {
        Application application = new Application(
                ID,
                EMPLOYEE_ID,
                TYPE_OF_APPLICATION_ID,
                TYPE_OF_RECEIPT_ID,
                CATEGORY_ID,
                STATUS_ID,
                DATE_OF_CREATION,
                DATE_OF_CHANGE
        );

        ApplicationService.applicationDatabase.add(application);

        int newCategoryID = 2;

        ApplicationService.updateApplication(ID, newCategoryID);

        Application applicationFromDB = ApplicationService.applicationDatabase.get(0);

        assertEquals(ID, applicationFromDB.getId());
        assertEquals(EMPLOYEE_ID, applicationFromDB.getEmployeeId());
        assertEquals(TYPE_OF_APPLICATION_ID, applicationFromDB.getTypeOfApplicationId());
        assertEquals(TYPE_OF_RECEIPT_ID, applicationFromDB.getTypeOfReceiptId());
        assertEquals(newCategoryID, applicationFromDB.getCategoryId());
        assertEquals(STATUS_ID, applicationFromDB.getStatusId());
        assertEquals(DATE_OF_CREATION, applicationFromDB.getDateOfCreation());
        assertEquals(DATE_OF_CHANGE, applicationFromDB.getDateOfChange());

        ApplicationService.applicationDatabase.remove(0);
    }

    @Test
    void shouldDeleteThrowApplicationNotFoundException() {
        Application application = new Application(
                ID,
                EMPLOYEE_ID,
                TYPE_OF_APPLICATION_ID,
                TYPE_OF_RECEIPT_ID,
                CATEGORY_ID,
                STATUS_ID,
                DATE_OF_CREATION,
                DATE_OF_CHANGE
        );

        ApplicationService.applicationDatabase.add(application);

        int newID = 2;

        assertThrows(ApplicationNotFoundException.class,
                () -> ApplicationService.deleteApplication(newID),
                "shouldDeleteThrowApplicationNotFoundException");

        ApplicationService.applicationDatabase.remove(0);
    }

    @Test
    void shouldUpdateThrowApplicationNotFoundException() {
        assertThrows(ApplicationNotFoundException.class,
                () -> ApplicationService.updateApplication(ID, EMPLOYEE_ID),
                "shouldUpdateThrowApplicationNotFoundException");
    }

    @Test
    void shouldAddThrowApplicationDismissedEmployee() {
        int employeeDismissedStatus = 0;

        Employee employee = new Employee(
                1,
                EMPLOYEE_ID,
                "NAME",
                "SURNAME",
                "PATRONYMIC",
                LocalDate.of(2000, 12, 31),
                LocalDate.of(2023, 4, 6),
                "EMAIL",
                "PHONE_NUMBER",
                "ADDRESS",
                employeeDismissedStatus
        );

        EmployeeService.employeeDatabase.add(employee);

        Application application = new Application(
                ID,
                EMPLOYEE_ID,
                TYPE_OF_APPLICATION_ID,
                TYPE_OF_RECEIPT_ID,
                CATEGORY_ID,
                STATUS_ID,
                DATE_OF_CREATION,
                DATE_OF_CHANGE
        );

        assertThrows(ApplicationDismissedEmployeeException.class,
                () -> ApplicationService.addApplication(application),
                "shouldAddThrowApplicationDismissedEmployee");

        EmployeeService.employeeDatabase.remove(0);
    }

    @Test
    void shouldAddThrowApplicationExceededLimitException() {
        Application application = new Application(
                ID,
                EMPLOYEE_ID,
                TYPE_OF_APPLICATION_ID,
                TYPE_OF_RECEIPT_ID,
                CATEGORY_ID,
                STATUS_ID,
                DATE_OF_CREATION,
                DATE_OF_CHANGE
        );

        for (int i = 0; i < ApplicationService.MAX_ORDER; i++) {
            ApplicationService.applicationDatabase.add(application);
        }

        assertThrows(ApplicationExceededLimitException.class,
                () -> ApplicationService.addApplication(application),
                "shouldAddThrowApplicationExceededLimitException");

        ApplicationService.applicationDatabase.subList(0, (ApplicationService.MAX_ORDER)).clear();
    }
}