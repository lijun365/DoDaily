package org.freefly.dodaily.rabbitmq.handler;

import org.freefly.dodaily.common.message.RabbitMqMailMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

public class SugarMarkHandler {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    public static void handle(RabbitMqMailMsg msg){
        System.out.println("In SugarMarkHandler:handle()");
        int type = msg.getSignal()%10000/1000;
        switch (type){
            case 1: // insert
                handleInsert(msg);
                break;
            case 2: // update
                break;
            case 3: // delete
                break;
            default:
                System.err.println("In SugarMarkHandler:handle(): No the service operation!");
        }
    }

    private static void handleInsert(RabbitMqMailMsg msg){

    }
}
