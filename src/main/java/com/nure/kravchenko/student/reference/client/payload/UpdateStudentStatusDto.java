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
public class UpdateStudentStatusDto {

    private String description;

    private LocalDate endDate;

    private boolean status;

}
