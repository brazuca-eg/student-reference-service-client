package com.nure.kravchenko.student.reference.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource("/application.properties")
public class Communication {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${student.reference.service.rest.url}")
    private String studentReferenceRestUrl;


    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public String getStudentReferenceRestUrl() {
        return studentReferenceRestUrl;
    }
}
