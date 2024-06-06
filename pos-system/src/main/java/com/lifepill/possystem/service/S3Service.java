package com.lifepill.possystem.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
    String uploadFile(String keyName, MultipartFile file) throws IOException;
    S3Object getFile(String keyName);
}
