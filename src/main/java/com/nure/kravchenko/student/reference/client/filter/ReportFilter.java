package com.nure.kravchenko.student.reference.client.filter;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportFilter {
    private String groupName;

    private String studentFullName;

    private LocalDate reportDate;

    private String reasonName;

}
