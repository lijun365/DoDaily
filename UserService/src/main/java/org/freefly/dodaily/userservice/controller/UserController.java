package org.freefly.dodaily.userservice.controller;

import io.jsonwebtoken.Claims;
import org.freefly.dodaily.userservice.common.ResultCode;
import org.freefly.dodaily.userservice.common.UserResult;
import org.freefly.dodaily.userservice.entity.User;
import org.freefly.dodaily.userservice.entity.UserCookie;
import org.freefly.dodaily.userservice.service.Impl.CookieService;
import org.freefly.dodaily.userservice.service.Impl.UserService;
import org.freefly.dodaily.userservice.tool.CommonTool;
import org.freefly.dodaily.userservice.tool.JWTTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    private final String cookieName = "DODAILY_USER";

    @Autowired
    private UserService userService;

    @Autowired
    private CookieService cookieService;

    @PostMapping("/register")
    public int Register(@RequestBody User user) {
        boolean flag = CommonTool.userCheck(user);
        if (flag) {
            int number = userService.getNumberOfUser() + 1;
            List<String> userNames = userService.getAllUserName();
            for (String item : userNames) {
                if (user.getName().equals(item)) {
                    System.out.println("Username repeat: " + item);
                    return ResultCode.INSERT_FAIL;
                }
            }
            user.setId(number);
            user.setPassword(CommonTool.md5Encrypt(user.getPassword()));
            int daoFlag = userService.Register(user);
            if (daoFlag == 1) {
                return ResultCode.INSERT_OK;
            } else {
                return ResultCode.INSERT_FAIL;
            }
        } else {
            return ResultCode.INSERT_NOTSUIT;
        }
    }

    @GetMapping("/login")
    @Transactional
    public UserResult login(@RequestBody User user, HttpServletResponse response) {
        if (user == null) {
            return UserResult.fail(0, "User is null.");
        }
        if (user.getName() == null || user.getPassword() == null) {
            return UserResult.fail(300, "Username or Password is null.");
        }
        User userByName = userService.getUserByName(user.getName());
        user.setPassword(CommonTool.md5Encrypt(user.getPassword()));
        if (userByName != null && user.getPassword().equals(userByName.getPassword())) {
            String token = JWTTool.generateToken(userByName.getId(), userByName.getName());
            UserCookie userCookie = new UserCookie(userByName.getId(), token, new Date());
            System.out.println("Had generated jwtToken(cookie): " + token);
            cookieService.deleteCookies(userByName.getId());
            int flag = cookieService.insertCookie(userCookie);
            if (flag == 1) {
                Cookie cookie = new Cookie(cookieName, token);
                response.addCookie(cookie);
                return UserResult.success(200, "Login success!");
            } else {
                return UserResult.fail(400, "Login Failed! Please Try again!");
            }
        } else {
            return UserResult.fail(0, "Username or password error!");
        }
    }

    @GetMapping("/logout/{id}")
    public int logout(@PathVariable("id") int userId, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, "");
        response.addCookie(cookie);
        try {
            cookieService.deleteCookies(userId);
        } catch (Exception e) {
            System.out.println("Cookie is deleted Wrong! UserId is: " + userId);
        }
        return ResultCode.DELETE_OK;
    }

    @GetMapping("/selectById")
    public User getUserById(String token) {
        Claims claims = JWTTool.getClaims(token);
        if (claims != null) {
            int userId = (int) claims.get("DODAILY_USERID");
            return userService.getUserById(userId);
        } else {
            return null;
        }
    }

    @PutMapping("/update")
    public int updateUser(@PathVariable("user") User user) {
        if (user == null || user.getId() == null) {
            return ResultCode.UPDATE_NULL;
        }
        int flag = userService.updateUser(user);
        if (flag == 1) {
            return ResultCode.UPDATE_OK;
        }
        return ResultCode.UPDATE_FAIL;
    }

    @DeleteMapping("/delete/{id}")
    public int deleteUser(@PathVariable("id") int id) {
        int flag = userService.deleteUser(id);
        if (flag == 1) {
            return ResultCode.DELETE_OK;
        }

        return ResultCode.DELETE_FAIL;
    }
}
