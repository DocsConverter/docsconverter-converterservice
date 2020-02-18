package com.github.docsconverter.docsconverterconverterservice.service;

import com.github.docsconverter.docsconverterconverterservice.to.Task;
import com.github.docsconverter.docsconverterconverterservice.util.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {
    private Logger logger = LoggerFactory.getLogger(ReceiveService.class);

    private final SendService sendService;

    public ReceiveService(SendService sendService) {
        this.sendService = sendService;
    }

    public void receive(String message){
        logger.info("Received from convert: " + message);

        Task task = TaskUtil.deserializeToObject(message);

        sendService.sendTask(task);

//        switch (task.getType()){
//            case TEXT:
//
//                break;
//            case PHOTO:
//            case DOCUMENT:
//            default:
//
//        }
    }
}
