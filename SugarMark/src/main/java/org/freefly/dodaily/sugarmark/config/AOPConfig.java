package org.freefly.dodaily.sugarmark.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.freefly.dodaily.sugarmark.client.UserServiceClient;
import org.freefly.dodaily.common.entity.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
public class AOPConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* org.freefly.dodaily.sugarmark.controller.*.insert(..))")
    public void pointcut() {
    }

    @After("pointcut()")
    public void sendRabbitMQ() {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            System.out.println("Cookies is null or empty!");
            return;
        }
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("dodaily_user")) {
                token = cookie.getValue();
                break;
            }
        }
        if (token == null) {
            System.out.println("Token is null!");
            return;
        }
        User userById = userServiceClient.getUserById(token);
        if (userById == null) {
            System.out.println("No the user!");
            return;
        }

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(
                result -> {
                    if(result.isAck()){
                        System.out.println("RabbitMq: message sent successfully!");
                    }else {
                        System.out.println("RabbitMq: message sent unsuccessfully!");
                    }
                },
                ex -> {
                    System.out.println("RabbitMq: Something ERROR even the ack can't be returned...");
                }
        );
        rabbitTemplate.convertAndSend("mail.exchange","mail_insert",userById, correlationData);
    }
}
