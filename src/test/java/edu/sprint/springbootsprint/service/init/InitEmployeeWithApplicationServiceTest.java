package edu.sprint.springbootsprint.service.init;

import edu.sprint.springbootsprint.service.ApplicationService;
import edu.sprint.springbootsprint.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class InitEmployeeWithApplicationServiceTest {

    @Test
    void shouldInitEmployeeWithApplication() {
        assertTrue(EmployeeService.employeeDatabase.isEmpty());
        assertTrue(ApplicationService.applicationDatabase.isEmpty());

        var mapInit = InitEmployeeWithApplicationService.init();

        boolean checkEqualNumberElements = mapInit.values()
                .stream()
                .allMatch(s -> s.size() == InitEmployeeWithApplicationService.COUNT_INIT_APPLICATION_FOR_EMPLOYEE);

        assertTrue(checkEqualNumberElements);

        int countInitEmployee = mapInit.keySet().size();

        int countInitApplication = Objects.requireNonNull(mapInit.values()
                        .stream()
                        .findFirst()
                        .orElse(null))
                .size();

        assertEquals(InitEmployeeWithApplicationService.COUNT_INIT_EMPLOYEE, countInitEmployee);
        assertEquals(InitEmployeeWithApplicationService.COUNT_INIT_APPLICATION_FOR_EMPLOYEE, countInitApplication);

        EmployeeService.employeeDatabase.subList(0,
                (InitEmployeeWithApplicationService.COUNT_INIT_EMPLOYEE)).clear();

        ApplicationService.applicationDatabase.subList(0,
                (InitEmployeeWithApplicationService.COUNT_INIT_APPLICATION_FOR_EMPLOYEE)).clear();

        assertTrue(EmployeeService.employeeDatabase.isEmpty());
        assertTrue(ApplicationService.applicationDatabase.isEmpty());
    }
}