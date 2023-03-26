package com.nure.kravchenko.student.reference.client.service;

import com.nure.kravchenko.student.reference.client.Communication;
import com.nure.kravchenko.student.reference.client.payload.*;
import com.nure.kravchenko.student.reference.client.server.*;
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

    public StudentDto getStudentById(Long id, String token) {
        ResponseEntity<StudentDto> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/admins/students/" + id,
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token), StudentDto.class);

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

    public List<StudentDto> getWaitingApproveStudents(String token) {
        ResponseEntity<List<StudentDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/admins/approve/students",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<StudentDto>>() {
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

    public List<StudentGroupDto> getAllGroups(String token) {
        ResponseEntity<List<StudentGroupDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/admins/groups",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<StudentGroupDto>>() {
                        });

        return responseEntity.getBody();
    }


    public List<SpecialityDto> getAllSpecialities(String token) {
        ResponseEntity<List<SpecialityDto>> responseEntity = communication.getRestTemplate()
                .exchange(communication.getStudentReferenceRestUrl() + "/admins/specialities",
                        HttpMethod.GET, createHttpEntityWithAuthorizationToken(token),
                        new ParameterizedTypeReference<List<SpecialityDto>>() {
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

    public StudentDto approveStudentRegistration(Long id, ApproveStudentRegisterDto approveStudentRegisterDto, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<ApproveStudentRegisterDto> approveWorkerDtoHttpEntity =
                new HttpEntity<>(approveStudentRegisterDto, headers);

        ResponseEntity<StudentDto> responseEntity = communication.getRestTemplate()
                .postForEntity(communication.getStudentReferenceRestUrl() + "/admins/students/" + id + "/approve",
                        approveWorkerDtoHttpEntity,
                        StudentDto.class);

        return responseEntity.getBody();
    }

    public FacultyDto createFaculty(CreateFacultyDto createFacultyDto, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<CreateFacultyDto> createFacultyDtoHttpEntity = new HttpEntity<>(createFacultyDto, headers);

        ResponseEntity<FacultyDto> responseEntity = communication.getRestTemplate()
                .postForEntity(communication.getStudentReferenceRestUrl() + "/admins/faculty/",
                        createFacultyDtoHttpEntity,
                        FacultyDto.class);

        return responseEntity.getBody();
    }

    public SpecialityDto createSpeciality(CreateSpecialityDto createSpecialityDto, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<CreateSpecialityDto> createSpecialityDtoHttpEntity = new HttpEntity<>(createSpecialityDto, headers);

        ResponseEntity<SpecialityDto> responseEntity = communication.getRestTemplate()
                .postForEntity(communication.getStudentReferenceRestUrl() + "/admins/speciality",
                        createSpecialityDtoHttpEntity,
                        SpecialityDto.class);

        return responseEntity.getBody();
    }

    public StudentGroupDto createGroup(CreateGroupDto createGroupDto, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<CreateGroupDto> createSpecialityDtoHttpEntity = new HttpEntity<>(createGroupDto, headers);

        ResponseEntity<StudentGroupDto> responseEntity = communication.getRestTemplate()
                .postForEntity(communication.getStudentReferenceRestUrl() + "/admins/group",
                        createSpecialityDtoHttpEntity,
                        StudentGroupDto.class);

        return responseEntity.getBody();
    }

    private HttpEntity<String> createHttpEntityWithAuthorizationToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(headers);
    }

}
