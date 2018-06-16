package com.developer.server.authenticplaces.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class FileUtils {

    public void saveImage(String imageBase64, Integer identifierMarker, Integer identifierSnapshot) {
        byte[] decodedString = Base64.decodeBase64(imageBase64);
//        File folder = new File(Paths.get("").toAbsolutePath().toString()
//                + File.separator + identifierMarker);
        File folder = new File(File.separator + identifierMarker);
        if (!folder.exists()){
            if(!folder.mkdir()){
                return;
            }
        }
        File image = new File(folder, identifierSnapshot + ".png");
        try (OutputStream outputStream = new FileOutputStream(image)) {
            outputStream.write(decodedString);
            outputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
