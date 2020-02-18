package com.github.docsconverter.docsconverterconverterservice.service;

import com.github.docsconverter.docsconverterconverterservice.command.ImageToPDFCommandHandlerImpl;
import com.github.docsconverter.docsconverterconverterservice.command.TextToPDFCommandHandlerImpl;
import com.github.docsconverter.docsconverterconverterservice.command.TextToTXTCommandHandlerImpl;
import com.github.docsconverter.docsconverterconverterservice.enums.Command;
import com.github.docsconverter.docsconverterconverterservice.enums.FileType;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.github.docsconverter.docsconverterconverterservice.enums.Command.TO_PDF;
import static com.github.docsconverter.docsconverterconverterservice.util.FileUtil.createTempFile;
import static com.github.docsconverter.docsconverterconverterservice.util.FileUtil.setExtension;
import static com.github.docsconverter.docsconverterconverterservice.util.TaskUtil.getName;

@Service
public class ConvertService {

    public File convert(long chatId, String url, FileType type, Command command) throws IOException{
        String name = getName(url);

        File file = createTempFile(chatId, name);
        File fileOutput = createTempFile(chatId, name);

        FileUtils.copyURLToFile(new URL(url), file);

        switch (type) {
            case PHOTO:
                if (TO_PDF.equals(command)) {
                    new ImageToPDFCommandHandlerImpl()
                            .execute(file, fileOutput);

                    fileOutput = setExtension(fileOutput, name, "PDF");
                }
                break;
            case DOCUMENT:
                break;
        }

        return fileOutput;
    }

    public File convertText(long chatId, String text, Command command) throws IOException {
        String name = !text.contains(" ") ?
                text.length() < 10 ? text : text.substring(0, 1) :
                text.split(" ")[0];

        File file = createTempFile(chatId, name);

        switch (command){
            case TO_TXT:
                new TextToTXTCommandHandlerImpl()
                        .execute(text, file);

                file = setExtension(file, name, "txt");
                break;
            case TO_PDF:
                new TextToPDFCommandHandlerImpl()
                        .execute(text, file);

                file = setExtension(file, name, "pdf");
                break;
        }

        return file;
    }

    public void convertToText(long chatId, String url, FileType type) throws IOException{

    }
}
