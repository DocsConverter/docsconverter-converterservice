package com.github.docsconverter.docsconverterconverterservice.service;

import com.github.docsconverter.docsconverterconverterservice.command.TextToPDFCommandHandlerImpl;
import com.github.docsconverter.docsconverterconverterservice.command.TextToTXTCommandHandlerImpl;
import com.github.docsconverter.docsconverterconverterservice.enums.Command;
import com.github.docsconverter.docsconverterconverterservice.enums.FileType;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static com.github.docsconverter.docsconverterconverterservice.util.FileUtil.setExtension;

@Service
public class ConvertService {

    public void convert(String url, FileType type, Command command, File output) throws IOException{

    }

    public void convertText(String text, Command command, File output) throws IOException {
        switch (command){
            case TO_TXT:
                new TextToTXTCommandHandlerImpl()
                        .execute(text, output);

                setExtension(output, "txt");
                break;
            case TO_PDF:
                new TextToPDFCommandHandlerImpl()
                        .execute(text, output);

                setExtension(output, "txt");
                break;
        }
    }

    public void convertToText(String url, FileType type) throws IOException{

    }
}
