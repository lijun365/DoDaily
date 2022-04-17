package org.freefly.dodaily.rabbitmq;

import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class,args);
    }

    @Bean
    public MessageConverter getMsgConvertor(){
        return new Jackson2JsonMessageConverter();
    }

    // Set the republishment operation for rabbitmq failed messages
//    @Bean
//    public MessageRecoverer messageRecoverer(){
//        return new RepublishMessageRecoverer();
//    }
}
