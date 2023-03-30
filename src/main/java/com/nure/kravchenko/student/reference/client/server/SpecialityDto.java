package com.nure.kravchenko.student.reference.client.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialityDto {

    private Long id;

    private String name;

    private String shortName;

    private Integer number;

    private String educationalProgram;

}
