package com.github.docsconverter.docsconverterconverterservice.mq;

import com.github.docsconverter.docsconverterconverterservice.service.ReceiveService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.github.docsconverter.docsconverterconverterservice.mq.RabbitConfiguration.CONVERT_QUEUE;

@EnableRabbit
@Component
public class TaskListener {

    private final ReceiveService service;

    public TaskListener(ReceiveService service) {
        this.service = service;
    }

    @RabbitListener(queues = CONVERT_QUEUE)
    public void processConvert(String message) {
        service.receive(message);
    }
}
