package com.github.docsconverter.docsconverterconverterservice.command;

import java.io.*;

public class TextToTXTCommandHandlerImpl implements CommandHandler<Void, String, File> {

    @Override
    public Void execute(String text, File output) throws IOException {
        try(FileOutputStream outputStream = new FileOutputStream(output);
            PrintStream printStream = new PrintStream(outputStream)){
            printStream.println(text);
        }
        return null;
    }
}
