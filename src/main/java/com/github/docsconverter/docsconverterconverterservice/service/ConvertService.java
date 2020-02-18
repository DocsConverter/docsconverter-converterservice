package com.github.docsconverter.docsconverterconverterservice.service;

import com.github.docsconverter.docsconverterconverterservice.command.TextToTXTCommandHandlerImpl;
import com.github.docsconverter.docsconverterconverterservice.enums.Command;
import com.github.docsconverter.docsconverterconverterservice.enums.FileType;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ConvertService {

    public void convert(String url, FileType type, Command command, File output) throws IOException{

    }

    public void convertText(String text, Command command, File output) throws IOException {
        switch (command){
            case TO_TXT:
                new TextToTXTCommandHandlerImpl()
                        .execute(text, output);
        }
    }

    public void convertToText(String url, FileType type) throws IOException{

    }
}
