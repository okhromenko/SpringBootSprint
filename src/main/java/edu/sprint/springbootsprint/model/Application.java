package edu.sprint.springbootsprint.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Application {
    private final int id;
    private int employeeId;
    private int typeOfApplicationId;
    private int typeOfReceiptId;
    private int categoryId;
    private int statusId;
    private LocalDate dateOfCreation;
    private LocalDate dateOfChange;
}
