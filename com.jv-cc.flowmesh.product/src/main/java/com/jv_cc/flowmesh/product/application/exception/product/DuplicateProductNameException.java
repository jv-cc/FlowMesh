package com.jv_cc.flowmesh.product.application.exception.product;

import com.jv_cc.flowmesh.product.application.exception.Error;
import com.jv_cc.flowmesh.product.application.exception.ProductException;
import org.springframework.http.HttpStatus;

public class DuplicateProductNameException extends ProductException{

    public DuplicateProductNameException() {
        super(Error.DUPLICATE_PRODUCT_NAME, HttpStatus.BAD_REQUEST);
    }
}
