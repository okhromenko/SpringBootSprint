package edu.sprint.springbootsprint.service.init;

import edu.sprint.springbootsprint.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitEmployeeWithoutApplicationServiceTest {

    @Test
    void shouldInitEmployeeWithoutApplication() {
        assertTrue(EmployeeService.employeeDatabase.isEmpty());

        var listEmployee = InitEmployeeWithoutApplicationService.init();

        assertEquals(InitEmployeeWithoutApplicationService.COUNT_INIT_EMPLOYEE,
                listEmployee.size());

        EmployeeService.employeeDatabase.subList(0,
                (InitEmployeeWithoutApplicationService.COUNT_INIT_EMPLOYEE)).clear();

        assertTrue(EmployeeService.employeeDatabase.isEmpty());
    }
}