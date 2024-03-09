package com.lifepill.possystem.helper;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class SaveImageHelper {

    public static byte[] saveImage(MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(image.getOriginalFilename());
                return image.getBytes();
            } catch (IOException e) {
                throw new RuntimeException("Failed to save image", e);
            }
        }
        return null;
    }
}
