package com.nure.kravchenko.student.reference.client.payload;

import lombok.Data;

@Data
public class CreateStudentPayload {
    private String name;
    private String surname;
    private String fatherhood;
    private String email;

    private String password;
}
