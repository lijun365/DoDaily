package org.freefly.dodaily.sugarmark.client.fallback;

import org.freefly.dodaily.sugarmark.client.UserServiceClient;
import org.freefly.dodaily.common.entity.User;

public class UserServiceFallback implements UserServiceClient {
    @Override
    public User getUserById(String token) {
        return null;
    }
}
