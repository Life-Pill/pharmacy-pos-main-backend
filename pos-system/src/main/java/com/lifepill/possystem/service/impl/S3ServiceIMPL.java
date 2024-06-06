package com.lifepill.possystem.service.impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.lifepill.possystem.service.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
@Log4j2
public class S3ServiceIMPL implements S3Service {

    private final AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.region}")
    private String awsRegion;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public String uploadFile(String keyName, MultipartFile file) throws IOException {
        log.info("Uploading file with key: " + keyName);
        s3client.putObject(bucketName, keyName, file.getInputStream(), null);
        String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s",
                bucketName, Regions.fromName(awsRegion).getName(), keyName);
        log.info("File uploaded to: " + fileUrl);
        return fileUrl;
    }

    public S3Object getFile(String keyName) {
        log.info("Retrieving file with key: " + keyName);
        return s3client.getObject(bucketName, keyName);
    }
}
