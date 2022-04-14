package org.freefly.dodaily.sugarmark.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.freefly.dodaily.sugarmark.client.UserServiceClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserServiceClient userServiceClient;

    @Pointcut("execution(* org.freefly.dodaily.sugarmark.controller.*.insert(..))")
    public void pointcut(){}

    @After("pointcut()")
    public void sendRabbitMQ(){

    }
}
