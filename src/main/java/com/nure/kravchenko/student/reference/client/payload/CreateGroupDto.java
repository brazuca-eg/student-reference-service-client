package com.nure.kravchenko.student.reference.client.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGroupDto {

    private String name;

    private LocalDate startYear;

    private LocalDate endYear;

    private String learnForm;

    private String degreeForm;

    private Long specialityId;

}
