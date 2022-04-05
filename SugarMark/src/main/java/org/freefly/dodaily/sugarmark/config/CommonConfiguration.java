package org.freefly.dodaily.sugarmark.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public MessageConverter getMsgConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
