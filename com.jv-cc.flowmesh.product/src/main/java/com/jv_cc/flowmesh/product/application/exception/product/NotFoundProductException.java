package com.jv_cc.flowmesh.product.application.exception.product;

import com.jv_cc.flowmesh.product.application.exception.Error;
import com.jv_cc.flowmesh.product.application.exception.ProductException;
import org.springframework.http.HttpStatus;

public class NotFoundProductException extends ProductException {

    public NotFoundProductException() {
        super(Error.NOT_FOUND_PRODUCT, HttpStatus.NOT_FOUND);
    }
}
