package org.freefly.dodaily.rabbitmq.listener;

import org.freefly.dodaily.common.message.RabbitMqMailMsg;
import org.freefly.dodaily.rabbitmq.handler.SugarMarkHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqListener {

    @RabbitListener(queues = {"mail.queue"})
    public void doMail(RabbitMqMailMsg msg) {
        System.out.println("In RabbitMqListener:doMail(): receive signal from mail.queue.");
        int type = msg.getSignal() / 10000;
        switch (type) {
            case 1: // userservice msg
                break;
            case 2: // sugarmark msg
                System.out.println("In RabbitMqListener:doMail(): receive sugarmark signal.");
                SugarMarkHandler.handle(msg);
                break;
            default:
                System.err.println("In RabbitMqListener:doMail(): No the service!");
        }
    }
}
