package org.freefly.dodaily.controller;

import org.freefly.dodaily.entity.PaidInfo;
import org.freefly.dodaily.service.PaidInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller for paid info service
 *
 * @author freefly365
 * @date 2523-07-30
 * */
@RestController
@RequestMapping("/paidinfo")
public class PaidInfoController {

    @Autowired
    private PaidInfoImpl paidInfoImpl;

    @PutMapping("/insert")
    public int insert(PaidInfo pi){
        return 0;
    }
}
