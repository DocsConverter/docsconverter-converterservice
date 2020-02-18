package com.github.docsconverter.docsconverterconverterservice.util;

import com.github.docsconverter.docsconverterconverterservice.enums.Command;

import java.io.File;
import java.io.IOException;

import static com.github.docsconverter.docsconverterconverterservice.service.FileService.ROOT_PATH;

public class FileUtil {

    public static File setExtension(File file, String extension){
        String path = file.getAbsolutePath();
        String[] splitPath = path.split(".");

        String newName = splitPath.length == 0 ?
                path + "." + extension :
                path.replace(splitPath[splitPath.length-1], extension);

        File newFile = new File(newName);
        file.renameTo(newFile);

        return newFile;
    }

    public static String getName(String path){
        return path.split("_")[2];
    }

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
        return System.getenv("URL")
                .concat("/file/download/" + chatId + "/" + name);
    }
}
