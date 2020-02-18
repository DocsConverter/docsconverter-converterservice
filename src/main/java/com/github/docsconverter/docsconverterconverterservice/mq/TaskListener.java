package com.github.docsconverter.docsconverterconverterservice.mq;

import com.github.docsconverter.docsconverterconverterservice.service.ReceiveService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class TaskListener {

    private final ReceiveService service;

    public TaskListener(ReceiveService service) {
        this.service = service;
    }

    @RabbitListener(queues = "convert")
    public void processConvert(String message) {
        service.receive(message);
    }
}
