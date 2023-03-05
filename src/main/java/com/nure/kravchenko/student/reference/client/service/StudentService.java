package com.nure.kravchenko.student.reference.client.service;

import com.nure.kravchenko.student.reference.client.Communication;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final Communication communication;

    public StudentService(Communication communication) {
        this.communication = communication;
    }

}
