package com.nure.kravchenko.student.reference.client.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSpecialityDto {

    private String name;

    private String shortName;

    private Integer number;

    private Long facultyId;

    private String educationalProgram;

}
