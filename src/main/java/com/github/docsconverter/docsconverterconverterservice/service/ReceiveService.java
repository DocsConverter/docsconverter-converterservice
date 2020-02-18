package com.github.docsconverter.docsconverterconverterservice.service;

import com.github.docsconverter.docsconverterconverterservice.enums.Command;
import com.github.docsconverter.docsconverterconverterservice.to.Task;
import com.github.docsconverter.docsconverterconverterservice.util.FileUtil;
import com.github.docsconverter.docsconverterconverterservice.util.TaskUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.github.docsconverter.docsconverterconverterservice.enums.FileType.DOCUMENT;
import static com.github.docsconverter.docsconverterconverterservice.enums.FileType.TEXT;
import static com.github.docsconverter.docsconverterconverterservice.util.FileUtil.createTempFile;
import static com.github.docsconverter.docsconverterconverterservice.util.FileUtil.getUrl;
import static com.github.docsconverter.docsconverterconverterservice.util.TaskUtil.getName;

@Service
public class ReceiveService {
    private Logger logger = LoggerFactory.getLogger(ReceiveService.class);

    private final SendService sendService;
    private final ConvertService convertService;

    public ReceiveService(SendService sendService, ConvertService convertService) {
        this.sendService = sendService;
        this.convertService = convertService;
    }

    public void receive(String message) {
        logger.info("Received from convert: " + message);

        Task task = TaskUtil.deserializeToObject(message);

        try {
            if(task.getAction().equals(Command.TO_TEXT)){
                convertService.convertToText(task.getChatId(), task.getUrl(), task.getType());
            } else {
                File file;

                if(task.getType().equals(TEXT)){
                    file = convertService.convertText(task.getChatId(), task.getText(), task.getAction());

                    task.setType(DOCUMENT);
                } else {
                    //FileUtils.copyURLToFile(new URL(task.getUrl()), file);
                    file = convertService.convert(task.getChatId(), task.getUrl(), task.getType(), task.getAction());
                }

                task.setUrl(getUrl(task.getChatId(), FileUtil.getName(file.getAbsolutePath())));
            }

            task.setCompleted(true);
        } catch (Exception e){
            e.printStackTrace();
        }

        sendService.sendTask(task);
    }
}
