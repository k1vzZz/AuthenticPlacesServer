package com.developer.server.authenticplaces.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;

@Component
public class FileUtils {

    public void saveImage(String imageBase64, Integer identifierMarker, Integer identifierSnapshot) {
//        byte[] decodedString = Base64.decodeBase64(imageBase64);
//        byte[] decodedString = Base64.getEncoder().encodeToString();
        byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
        File folder = new File(Paths.get("").toAbsolutePath().toString()
                + File.separator + identifierMarker);
//        File folder = new File(File.separator + identifierMarker);
        if (!folder.exists()){
            folder.mkdir();
        }
        File image = new File(folder, identifierSnapshot + ".jpg");
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(image))) {
            outputStream.write(decodedString);
            outputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
