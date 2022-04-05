package org.freefly.dodaily.sugarmark.controller;

import org.freefly.dodaily.sugarmark.common.ResultCode;
import org.freefly.dodaily.sugarmark.common.SugarMarkPage;
import org.freefly.dodaily.sugarmark.common.SugarMarkResult;
import org.freefly.dodaily.sugarmark.entity.SugarMark;
import org.freefly.dodaily.sugarmark.service.Impl.SugarMarkServiceImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
@RestController
public class SugarMarkController {

    @Autowired
    private SugarMarkServiceImpl service;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/select/{curPage}/{pageSize}")
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
        if (list != null && list.size() > 0) {
            for (SugarMark item : list) {
                if (item.getcDate() == null) {
                    item.setCDate(new Date());
                }
            }
            int flag = service.createSugarMark(list);
            if (flag == list.size()) {
                Message message = new Message(ResultCode.S_INSERT_OK.getBytes(StandardCharsets.UTF_8), new MessageProperties());
                //rabbitTemplate.convertAndSend();
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
