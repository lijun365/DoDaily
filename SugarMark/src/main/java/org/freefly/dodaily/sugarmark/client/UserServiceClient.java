package org.freefly.dodaily.sugarmark.client;

import org.freefly.dodaily.sugarmark.client.fallback.UserServiceFallback;
import org.freefly.dodaily.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "userservice", fallback = UserServiceFallback.class)
public interface UserServiceClient {

    @GetMapping("/selectById")
    public User getUserById(String token);
}
