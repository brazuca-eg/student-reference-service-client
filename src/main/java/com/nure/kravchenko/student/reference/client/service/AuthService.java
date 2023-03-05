package com.nure.kravchenko.student.reference.client.service;

import com.nure.kravchenko.student.reference.client.Communication;
import com.nure.kravchenko.student.reference.client.payload.LoginDto;
import com.nure.kravchenko.student.reference.client.payload.RegistrationDto;
import com.nure.kravchenko.student.reference.client.server.UserLoggedInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Communication communication;

    @Autowired
    public AuthService(Communication communication) {
        this.communication = communication;
    }

    public UserLoggedInDto login(LoginDto loginDto) {
        ResponseEntity<UserLoggedInDto> responseEntity = communication.getRestTemplate()
                .postForEntity(communication.getStudentReferenceRestUrl() + "/auth/login",
                        loginDto, UserLoggedInDto.class);
        return responseEntity.getBody();
    }

    public Boolean register(RegistrationDto registrationDto) {
        ResponseEntity<Boolean> responseEntity = communication.getRestTemplate()
                .postForEntity(communication.getStudentReferenceRestUrl() + "/auth/register",
                        registrationDto, Boolean.class);

        return responseEntity.getBody();
    }

}
