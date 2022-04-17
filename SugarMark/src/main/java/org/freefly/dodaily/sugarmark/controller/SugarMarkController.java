package org.freefly.dodaily.sugarmark.controller;

import org.freefly.dodaily.sugarmark.client.UserServiceClient;
import org.freefly.dodaily.sugarmark.common.ResultCode;
import org.freefly.dodaily.sugarmark.common.SugarMarkPage;
import org.freefly.dodaily.sugarmark.common.SugarMarkResult;
import org.freefly.dodaily.common.entity.SugarMark;
import org.freefly.dodaily.common.entity.User;
import org.freefly.dodaily.sugarmark.service.Impl.SugarMarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
@RestController
public class SugarMarkController {

    private final String cookieName = "dodaily_user";
    @Autowired
    private SugarMarkServiceImpl service;
    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping("/select/{curPage}/{pageSize}")
    public SugarMarkResult query(HttpServletRequest request, @PathVariable("curPage") int curPage, @PathVariable("pageSize") int pageSize, @RequestBody(required = false) SugarMark vo) {
        // Test
        System.out.println("curPage: "+curPage+", pageSize: "+pageSize);

        //

        SugarMarkPage sugarMarkPage = new SugarMarkPage(curPage, pageSize);
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return SugarMarkResult.getFail("The cookies is null or empty!");
        }
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                token = cookie.getValue();
            }
        }
        if (token == null) {
            return SugarMarkResult.getFail("The token is null!");
        }
        // Invoke userservice to get user
        User userById = userServiceClient.getUserById(token);

        if (userById != null) {
            final Integer userId = userById.getId();
            List<SugarMark> list = service.findResultByPage(vo, sugarMarkPage, userId);
            if (list != null && list.size() != 0) {
                sugarMarkPage.setTotal(service.getTotalNumber(vo, userId));
                SugarMarkResult success = SugarMarkResult.getSuccess(list);
                success.setSugarMarkPage(sugarMarkPage);
                return success;
            } else {
                sugarMarkPage.setTotal(service.getTotalNumber(vo, userId));
                SugarMarkResult fail = SugarMarkResult.getFail("Sorry, The result is empty!");
                fail.setSugarMarkPage(sugarMarkPage);
                return fail;
            }
        }

        return SugarMarkResult.getFail("User not found!");
    }

    @PostMapping("/insert")
    public int insert(@RequestBody(required = false) List<SugarMark> list, HttpServletRequest request) {
        if (list != null && list.size() > 0) {
            for (SugarMark item : list) {
                if (item.getcDate() == null) {
                    item.setCDate(new Date());
                }
                if (item.getuDate() == null) {
                    item.setUDate(new Date());
                }
            }
            Cookie[] cookies = request.getCookies();
            if (cookies == null || cookies.length == 0) {
                return ResultCode.INSERT_NOTSUIT;
            }
            String token = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    token = cookie.getValue();
                    break;
                }
            }
            if (token == null) {
                return ResultCode.INSERT_NOTSUIT;
            }
            User userById = userServiceClient.getUserById(token);
            //test
            SugarMark sugarMark = list.get(0);
            System.out.println(sugarMark);
            //
            int flag = service.createSugarMark(list, userById.getId());
            if (flag == list.size()) {
                return ResultCode.INSERT_OK;
            } else if (flag > 0) {
                return ResultCode.INSERT_NOTALL;
            } else {
                return ResultCode.INSERT_FAIL;
            }
        } else {
            return ResultCode.INSERT_NULL;
        }
    }

    @PutMapping("/update")
    public int update(@RequestBody SugarMark vo) {
        if (vo == null) {
            return ResultCode.UPDATE_NULL;
        }
        vo.setUDate(new Date());
        int flag = service.updateSugarMark(vo);
        if (flag == 1) {
            return ResultCode.UPDATE_OK;
        } else {
            return ResultCode.UPDATE_FAIL;
        }
    }

    @DeleteMapping("/delete")
    public int delete(@RequestBody List<Integer> ids) {
        if (ids != null && ids.size() > 0) {
            int flag = service.deleteSugarMark(ids);
            if (flag == ids.size()) {
                return ResultCode.DELETE_OK;
            } else if (flag > 0) {
                return ResultCode.DELETE_NOTALL;
            } else {
                return ResultCode.DELETE_FAIL;
            }
        } else {
            return ResultCode.DELETE_NULL;
        }
    }
}
