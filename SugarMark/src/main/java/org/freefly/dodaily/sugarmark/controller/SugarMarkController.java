package org.freefly.dodaily.sugarmark.controller;

import org.freefly.dodaily.sugarmark.common.SugarMarkPage;
import org.freefly.dodaily.sugarmark.common.SugarMarkResult;
import org.freefly.dodaily.sugarmark.entity.SugarMark;
import org.freefly.dodaily.sugarmark.service.Impl.SugarMarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Component
@RestController
public class SugarMarkController {

    @Autowired
    private SugarMarkServiceImpl service;

    @GetMapping("/query/{curPage}/{pageSize}")
    public SugarMarkResult query(@PathVariable("curPage") int curPage, @PathVariable("pageSize") int pageSize, @RequestBody SugarMark vo) {
        SugarMarkPage sugarMarkPage = new SugarMarkPage(curPage, pageSize);
        List<SugarMark> list = service.findResultByPage(vo, sugarMarkPage);
        if (list != null && list.size() != 0) {
            sugarMarkPage.setTotal(service.getTotalNumber(vo));
            SugarMarkResult success = SugarMarkResult.getSuccess(list);
            success.setSugarMarkPage(sugarMarkPage);
            return success;
        } else {
            sugarMarkPage.setTotal(service.getTotalNumber(vo));
            SugarMarkResult fail = SugarMarkResult.getFail("Sorry, The result is empty!");
            fail.setSugarMarkPage(sugarMarkPage);
            return fail;
        }
    }

    @PostMapping("/insert")
    public int insert(@RequestBody List<SugarMark> list) {
        if(list != null && list.size() >0) {
            for (SugarMark item : list) {
                if (item.getcDate() == null) {
                    item.setCDate(new Date());
                }
            }
            int flag = service.createSugarMark(list);
            if (flag == list.size()) {
                return 200;
            } else if (flag > 0) {
                return 300;
            }
        }

        return 400;
    }
}
