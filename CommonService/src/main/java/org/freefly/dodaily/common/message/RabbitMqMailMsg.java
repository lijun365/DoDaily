package org.freefly.dodaily.common.message;

import lombok.Data;

@Data
public class RabbitMqMailMsg {

    private int signal;
    private Object object;
}
