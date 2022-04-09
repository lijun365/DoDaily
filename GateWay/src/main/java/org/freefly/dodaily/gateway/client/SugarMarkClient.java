package org.freefly.dodaily.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "sugarmark")
public interface SugarMarkClient {

    @GetMapping("/hello")
    public String hello();
}
