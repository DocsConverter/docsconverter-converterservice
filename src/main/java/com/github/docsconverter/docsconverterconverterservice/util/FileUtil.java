package com.github.docsconverter.docsconverterconverterservice.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static com.github.docsconverter.docsconverterconverterservice.service.FileService.ROOT_PATH;

public class FileUtil {

    public static File createTempFile(long chatId, String name) throws IOException {
        return File.createTempFile(chatId + "_", "_" + name);
    }

    public static File getTempFile(Long chatId, String name) throws IOException {
        File[] list = new File(ROOT_PATH).listFiles();

        if(list!= null) {
            for (File file : list) {
                String[] fileName = file.getName().split("_");

                if (fileName.length == 3
                        && fileName[0].equals(chatId.toString())
                        && fileName[2].equals(name)) {
                    return file;
                }
            }
        }

        throw new IOException("File not created OR Root is'nt correct!");
    }

    public static String getUrl(long chatId, String name){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/download/{chatId}/{name}")
                .buildAndExpand(chatId, name)
                .toString();
    }
}