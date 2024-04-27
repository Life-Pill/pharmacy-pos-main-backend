package com.lifepill.possystem.helper;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The type Save image helper.
 */
public class SaveImageHelper {

    /**
     * Save image byte [ ].
     *
     * @param image the image
     * @return the byte [ ]
     */
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
