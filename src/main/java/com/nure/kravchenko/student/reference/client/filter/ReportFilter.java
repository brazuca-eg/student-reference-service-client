package com.nure.kravchenko.student.reference.client.filter;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportFilter {
    private String groupName;

    private String studentFullName;

    private LocalDate reportStartDate;

    private LocalDate reportEndDate;

    private String reasonName;

    private String specialityName;

    private String educationalProgram;

}
