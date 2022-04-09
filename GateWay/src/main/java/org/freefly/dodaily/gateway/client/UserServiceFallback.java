package org.freefly.dodaily.gateway.client;

import org.springframework.http.HttpCookie;

public class UserServiceFallback implements UserServiceClient{
    @Override
    public int validate(HttpCookie cookie) {
        return 0;
    }
}
