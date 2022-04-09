package org.freefly.dodaily.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpCookie;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "userservice",fallback = UserServiceFallback.class)
public interface UserServiceClient {

    @GetMapping("/validate")
    public int validate(HttpCookie cookie);
}
