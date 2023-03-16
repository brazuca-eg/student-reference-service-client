package com.nure.kravchenko.student.reference.client.server;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkerRequestDto {
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String reasonName;

    private String reasonDescription;

    private String groupName;

    private String studentFullName;

    private Long studentId;

}
