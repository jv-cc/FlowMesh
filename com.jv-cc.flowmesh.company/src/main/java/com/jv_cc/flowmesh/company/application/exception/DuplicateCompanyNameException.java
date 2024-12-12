package com.jv_cc.flowmesh.company.application.exception;

import org.springframework.http.HttpStatus;

public class DuplicateCompanyNameException extends CompanyException {
    public DuplicateCompanyNameException() {
        super(Error.DUPLICATE_COMPANY_NAME, HttpStatus.BAD_REQUEST);
    }
}
