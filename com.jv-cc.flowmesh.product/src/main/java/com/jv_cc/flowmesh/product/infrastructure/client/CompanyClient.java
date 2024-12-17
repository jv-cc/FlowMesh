package com.jv_cc.flowmesh.product.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company")
public interface CompanyClient {

    @GetMapping("/api/company/{companyId}/client")
    boolean existsCompanyBy(@PathVariable Long companyId);

}
