package org.freefly.dodaily.userservice.controller;

import org.freefly.dodaily.userservice.common.ResultCode;
import org.freefly.dodaily.userservice.common.UserResult;
import org.freefly.dodaily.userservice.entity.User;
import org.freefly.dodaily.userservice.entity.UserCookie;
import org.freefly.dodaily.userservice.service.Impl.CookieService;
import org.freefly.dodaily.userservice.service.Impl.UserService;
import org.freefly.dodaily.userservice.tool.CommonTool;
import org.freefly.dodaily.userservice.tool.JWTTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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
    public UserResult login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
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
            UserCookie userCookie = new UserCookie(userByName.getId(),token, new Date());
            cookieService.deleteCookies(userByName.getId());
            int flag = cookieService.insertCookie(userCookie);
            if(flag == 1) {
                Cookie cookie = new Cookie(cookieName, token);
                response.addCookie(cookie);
                return UserResult.success(200, "Login success!");
            }else {
                return UserResult.fail(400, "Login Failed! Please Try again!");
            }
        } else {
            return UserResult.fail(0, "Username or password error!");
        }
    }

    @GetMapping("/select/{id}")
    public UserResult getUserById(@PathVariable("id") int id) {
        // Pending
        return null;
    }

    @PutMapping("/update")
    public int updateUser(@PathVariable("user") User user) {
        if (user == null || user.getId() == null) {
            return ResultCode.UPDATE_NULL;
        }
        int flag = userService.updateUser(user);
        if(flag==1){
            return ResultCode.UPDATE_OK;
        }
        return ResultCode.UPDATE_FAIL;
    }

    @DeleteMapping("/delete/{id}")
    public int deleteUser(@PathVariable("id") int id) {
        int flag = userService.deleteUser(id);
        if(flag==1){
            return ResultCode.DELETE_OK;
        }

        return ResultCode.DELETE_FAIL;
    }
}
