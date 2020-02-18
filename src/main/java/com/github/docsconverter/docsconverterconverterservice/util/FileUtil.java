package com.github.docsconverter.docsconverterconverterservice.util;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

import static com.github.docsconverter.docsconverterconverterservice.service.FileService.ROOT_PATH;

public class FileUtil {

    public static File setExtension(File file, String name, String extension){

        String path = FilenameUtils.removeExtension(file.getAbsolutePath())
                .replace(name, name + "." + extension);

        File newFile = new File(path);
        file.renameTo(newFile);

        return newFile;
    }

    public static String getName(String path){
        return path.split("__")[2];
    }

    public static File createTempFile(long chatId, String name) throws IOException {
        return File.createTempFile(chatId + "__", "__" + name);
    }

    public static File getTempFile(Long chatId, String name) throws IOException {
        File[] list = new File(ROOT_PATH).listFiles();

        if(list!= null) {
            for (File file : list) {
                String[] fileName = file.getName().split("__");

                if (fileName.length == 3 &&
                        fileName[0].equals(chatId.toString()) &&
                        fileName[2].equals(name)) {
                    return file;
                }
            }
        }

        throw new IOException("File not created OR Root is'nt correct!");
    }

    public static String getUrl(long chatId, String name){
        return System.getenv("URL")
                .concat("/file/download/" + chatId + "/" + name);
    }
}
