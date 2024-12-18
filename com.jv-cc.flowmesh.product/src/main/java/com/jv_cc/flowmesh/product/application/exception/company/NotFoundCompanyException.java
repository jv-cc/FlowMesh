package com.jv_cc.flowmesh.product.application.exception.company;

import com.jv_cc.flowmesh.product.application.exception.Error;
import org.springframework.http.HttpStatus;

public class NotFoundCompanyException extends CompanyException {
    public NotFoundCompanyException() {
        super(Error.NOT_FOUND_COMPANY, HttpStatus.NOT_FOUND);
    }
}