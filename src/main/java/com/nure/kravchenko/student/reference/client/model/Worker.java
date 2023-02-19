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
public class Worker {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String fatherhood;
    private Boolean isAdmin;
    private String jobTitle;
    private Faculty faculty;
    private List<Request> requests;
}
