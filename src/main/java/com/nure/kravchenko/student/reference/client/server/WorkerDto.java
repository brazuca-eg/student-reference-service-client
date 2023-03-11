package com.nure.kravchenko.student.reference.client.server;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkerDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String fatherhood;

    private boolean isAdmin;

    private String jobTitle;

    private Character gender;

}
