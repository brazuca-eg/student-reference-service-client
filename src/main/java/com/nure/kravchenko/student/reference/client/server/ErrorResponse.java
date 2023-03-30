package com.nure.kravchenko.student.reference.client.server;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ErrorResponse {

    private String errorCode;
    private String errorDescription;

    public ErrorResponse errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ErrorResponse errorDescription(String errorDescriptionIn) {
        this.errorDescription = errorDescriptionIn;
        return this;
    }

}
