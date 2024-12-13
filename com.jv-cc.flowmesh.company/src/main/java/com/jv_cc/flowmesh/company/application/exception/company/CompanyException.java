package com.jv_cc.flowmesh.company.application.exception.company;

import com.jv_cc.flowmesh.company.application.exception.Error;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CompanyException extends RuntimeException {

    com.jv_cc.flowmesh.company.application.exception.Error error;

    HttpStatus httpStatus;

    public CompanyException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
