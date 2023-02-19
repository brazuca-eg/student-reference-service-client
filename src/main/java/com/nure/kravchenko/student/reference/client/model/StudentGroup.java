package com.nure.kravchenko.student.reference.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroup {
    private Long id;

    private String name;

    private LocalDate startYear;

    private LocalDate endYear;

    private String learnForm;

    private List<Student> students;

    private Speciality speciality;
}
