package com.nure.kravchenko.student.reference.client.service;

import com.nure.kravchenko.student.reference.client.Communication;
import com.nure.kravchenko.student.reference.client.payload.ApproveWorkerDto;
import com.nure.kravchenko.student.reference.client.server.FacultyDto;
import com.nure.kravchenko.student.reference.client.server.WorkerDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final Communication communication;

    public AdminService(Communication communication) {
        this.communication = communication;
    }

    public WorkerDto getAdminById(Long id, String token) {
        ResponseEntity<WorkerDto> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/admins/" + id,
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token), WorkerDto.class);

        return responseEntity.getBody();
    }

    public List<WorkerDto> getWaitingApproveWorkers(String token) {
        ResponseEntity<List<WorkerDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/admins/approve/workers",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<WorkerDto>>() {
                        });

        return responseEntity.getBody();
    }

    public List<FacultyDto> getAllFaculties(String token) {
        ResponseEntity<List<FacultyDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/admins/faculties",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<FacultyDto>>() {
                        });

        return responseEntity.getBody();
    }

    public WorkerDto approveWorkerRegistration(Long id, ApproveWorkerDto approveWorkerDto, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<ApproveWorkerDto> approveWorkerDtoHttpEntity = new HttpEntity<>(approveWorkerDto, headers);

        ResponseEntity<WorkerDto> responseEntity = communication.getRestTemplate()
                .postForEntity(communication.getStudentReferenceRestUrl() + "/admins/workers/" + id + "/approve",
                        approveWorkerDtoHttpEntity,
                        WorkerDto.class);

        return responseEntity.getBody();
    }

    private HttpEntity<String> createHttpEntityWithAuthorizationToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(headers);
    }

}
