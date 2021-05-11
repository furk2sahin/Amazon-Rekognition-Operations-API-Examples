package com.amazon.s3.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AmazonS3Service {
    String uploadFile(MultipartFile file, String bucketName);
}