package com.nure.kravchenko.student.reference.client.service;

import com.nure.kravchenko.student.reference.client.Communication;
import com.nure.kravchenko.student.reference.client.server.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private final Communication communication;

    public WorkerService(Communication communication) {
        this.communication = communication;
    }

    public WorkerDto getWorkerById(Long id, String token) {
        ResponseEntity<WorkerDto> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/workers/" + id,
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token), WorkerDto.class);

        return responseEntity.getBody();
    }

    public FacultyDto getWorkerFaculty(Long id, String token) {
        ResponseEntity<FacultyDto> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/workers/" + id + "/faculty",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token), FacultyDto.class);

        return responseEntity.getBody();
    }

    public List<ReasonDto> getAllRequestReasons(String token) {
        ResponseEntity<List<ReasonDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/workers/requests/reasons",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<ReasonDto>>() {
                        });

        return responseEntity.getBody();
    }

    public List<WorkerRequestDto> getAssignedWorkerRequests(Long id, boolean approved, String token) {
        ResponseEntity<List<WorkerRequestDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/workers/" + id + "/requests/assigned" +
                                "?approved=" + approved,
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<WorkerRequestDto>>() {
                        });

        return responseEntity.getBody();
    }

    public List<WorkerRequestDto> getNonAssignedRequestsByWorkerFaculty(Long id, String token) {
        ResponseEntity<List<WorkerRequestDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/workers/" + id + "/requests/nonAssigned",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<WorkerRequestDto>>() {
                        });

        return responseEntity.getBody();
    }

    public RequestDto approveRequest(Long workerId, Long requestId, boolean approve, String comment, String token) {
        ResponseEntity<RequestDto> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/workers/" + workerId +
                                "/requests/" + requestId + "?approve=" + approve + "&comment=" + comment,
                        HttpMethod.POST, createHttpEntityWithAuthorizationToken(token),
                        RequestDto.class);

        return responseEntity.getBody();
    }

    public ByteArrayResource downloadReport(String fileName, String token) {
        ResponseEntity<ByteArrayResource> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/workers/requests/download/" + fileName,
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        ByteArrayResource.class);

        return responseEntity.getBody();
    }

    private HttpEntity<String> createHttpEntityWithAuthorizationToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(headers);
    }
}
