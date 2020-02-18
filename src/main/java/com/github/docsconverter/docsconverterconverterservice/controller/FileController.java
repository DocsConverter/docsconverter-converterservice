package com.github.docsconverter.docsconverterconverterservice.controller;

import com.github.docsconverter.docsconverterconverterservice.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.github.docsconverter.docsconverterconverterservice.util.FileUtil.getTempFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private Logger log = LoggerFactory.getLogger("FILE_CONTROLLER");

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(path = "/download/{chatId}/{name}")
    public void download(HttpServletResponse response, @PathVariable Long chatId, @PathVariable String name) throws IOException {
        log.info("DOWNLOAD FILENAME = {}, CHAT_ID = {}", name, chatId.toString());

        File file = getTempFile(chatId, name);

        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "inline; filename=\"" + name + "\"");

        fileService.download(file, response.getOutputStream());
    }
}
