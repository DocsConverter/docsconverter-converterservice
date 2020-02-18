package com.github.docsconverter.docsconverterconverterservice.mq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class ResultListener {

    @RabbitListener(queues = "convert")
    public void processConvert(String message) {
        System.out.println(message);
    }
}
