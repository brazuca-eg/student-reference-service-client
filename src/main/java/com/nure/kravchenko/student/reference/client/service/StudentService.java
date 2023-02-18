package com.nure.kravchenko.student.reference.client.service;

import com.nure.kravchenko.student.reference.client.Communication;
import com.nure.kravchenko.student.reference.client.model.Student;
import com.nure.kravchenko.student.reference.client.payload.StudentLoginPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final Communication communication;

    public StudentService(Communication communication) {
        this.communication = communication;
    }

    public Student login(StudentLoginPayload studentLoginPayload) {
        ResponseEntity<Student> responseEntity = communication.getRestTemplate()
                .postForEntity(communication.getStudentReferenceRestUrl() + "/student/login",
                        studentLoginPayload, Student.class);
        return responseEntity.getBody();
    }


}
