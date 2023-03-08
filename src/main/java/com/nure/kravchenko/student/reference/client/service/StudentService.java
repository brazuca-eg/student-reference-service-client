package com.nure.kravchenko.student.reference.client.service;

import com.nure.kravchenko.student.reference.client.Communication;
import com.nure.kravchenko.student.reference.client.payload.CreateRequestDto;
import com.nure.kravchenko.student.reference.client.server.ReasonDto;
import com.nure.kravchenko.student.reference.client.server.RequestDto;
import com.nure.kravchenko.student.reference.client.server.StudentDto;
import com.nure.kravchenko.student.reference.client.server.StudentGroupDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final Communication communication;

    public StudentService(Communication communication) {
        this.communication = communication;
    }

    public StudentDto getStudentById(Long id, String token) {
        ResponseEntity<StudentDto> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/students/" + id,
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token), StudentDto.class);

        return responseEntity.getBody();
    }

    public StudentGroupDto getGroupByStudent(Long id, String token){
        ResponseEntity<StudentGroupDto> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/students/" + id + "/group",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token), StudentGroupDto.class);

        return responseEntity.getBody();
    }

    public List<ReasonDto> getAllRequestReasonsForStudent(String token) {
        ResponseEntity<List<ReasonDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/students/" + "/reasons",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<ReasonDto>>() {
                        });

        return responseEntity.getBody();
    }

    public RequestDto createRequest(Long id, CreateRequestDto createRequestDto,  String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<CreateRequestDto> createRequestDtoHttpEntity = new HttpEntity<>(createRequestDto, headers);

        ResponseEntity<RequestDto> responseEntity = communication.getRestTemplate()
                .postForEntity(communication.getStudentReferenceRestUrl() + "/students/" + id + "/requests",
                        createRequestDtoHttpEntity,
                        RequestDto.class);

        return responseEntity.getBody();
    }

    public List<RequestDto> getRequestForStudent(Long id, String token) {
        ResponseEntity<List<RequestDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/students/" + id + "/requests",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<RequestDto>>() {
                        });

        return responseEntity.getBody();
    }

    private HttpEntity<String> createHttpEntityWithAuthorizationToken(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(headers);
    }

}
