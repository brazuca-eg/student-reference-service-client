package com.nure.kravchenko.student.reference.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String reason;

    private Student student;

}
