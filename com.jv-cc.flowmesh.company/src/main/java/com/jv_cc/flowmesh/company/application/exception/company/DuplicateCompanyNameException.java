package com.jv_cc.flowmesh.company.application.exception.company;

import com.jv_cc.flowmesh.company.application.exception.Error;
import org.springframework.http.HttpStatus;

public class DuplicateCompanyNameException extends CompanyException {
    public DuplicateCompanyNameException() {
        super(Error.DUPLICATE_COMPANY_NAME, HttpStatus.BAD_REQUEST);
    }
}
