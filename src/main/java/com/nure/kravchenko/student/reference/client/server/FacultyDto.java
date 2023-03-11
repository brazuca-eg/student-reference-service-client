package com.nure.kravchenko.student.reference.client.server;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FacultyDto {

    private Long id;

    private String name;

    private String shortName;

}
