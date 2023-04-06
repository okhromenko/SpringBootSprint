package edu.sprint.springbootsprint.service.create;

import edu.sprint.springbootsprint.model.Application;
import edu.sprint.springbootsprint.service.ApplicationService;

import java.time.LocalDate;

public class CreateApplication {
    private static int count;
    private static final LocalDate APPLICATION_DATE = LocalDate.of(2023, 4, 6);

    public static Application createApplication(int employeeId) {

        if (ApplicationService.applicationDatabase.isEmpty()) {
            count = 0;
        }

        count++;

        return new Application(
                count,
                employeeId,
                count,
                count,
                count,
                count,
                APPLICATION_DATE,
                APPLICATION_DATE
        );
    }
}
