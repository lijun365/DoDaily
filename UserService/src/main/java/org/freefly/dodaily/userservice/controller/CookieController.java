package org.freefly.dodaily.userservice.controller;

import io.jsonwebtoken.Claims;
import org.freefly.dodaily.userservice.entity.UserCookie;
import org.freefly.dodaily.userservice.service.Impl.CookieService;
import org.freefly.dodaily.userservice.tool.JWTTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @Autowired
    private CookieService cookieService;

    @GetMapping("/validate")
    public int validate(HttpCookie cookie) {
        String cookieValue = cookie.getValue();
        boolean flag = false;
        if (!JWTTool.volidateToken(cookieValue)) {
            return 0;
        }
        Claims claims = JWTTool.getClaims(cookieValue);
        int userId = (int) claims.get("DODAILY_USERID");
        UserCookie userCookie = cookieService.selectCookie(userId);
        if (userCookie != null && cookieValue.equals(userCookie.getCookie())) {
            flag = true;
        }
        return flag ? 1 : 0;
    }
}
