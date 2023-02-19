package com.nure.kravchenko.student.reference.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Speciality {
    private Long id;

    private String name;

    private String shortName;

    private Integer number;

    private List<StudentGroup> studentGroups;

    private Faculty faculty;
}
