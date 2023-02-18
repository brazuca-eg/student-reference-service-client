package com.nure.kravchenko.student.reference.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    private Long id;

    private String serialNumber;
    private String number;
    private LocalDate startDate;
    private LocalDate endDate;
    private Student student;
}
