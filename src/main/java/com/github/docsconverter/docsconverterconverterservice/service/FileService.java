package com.github.docsconverter.docsconverterconverterservice.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class FileService {
    public static final String ROOT_PATH = "/tmp";

    public void download(File file, OutputStream stream){
        try {
            FileUtils.copyFile(file, stream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


}
