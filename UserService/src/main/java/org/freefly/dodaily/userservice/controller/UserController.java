package org.freefly.dodaily.userservice.controller;

import org.freefly.dodaily.userservice.common.ResultCode;
import org.freefly.dodaily.userservice.common.UserResult;
import org.freefly.dodaily.userservice.entity.User;
import org.freefly.dodaily.userservice.service.Impl.UserService;
import org.freefly.dodaily.userservice.tool.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public int Register(@RequestBody User user) {
        boolean flag = CommonTool.userCheck(user);
        if (flag) {
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
        return null;
    }

    @GetMapping("/select/{id}")
    public UserResult getUserById(@PathVariable("id") int id) {

        return null;
    }

    @PutMapping("/update")
    public int updateUser(@PathVariable("user") User user) {

        return 0;
    }

    @DeleteMapping("/delete/{id}")
    public int deleteUser(@PathVariable("id") int id) {

        return 0;
    }
}
