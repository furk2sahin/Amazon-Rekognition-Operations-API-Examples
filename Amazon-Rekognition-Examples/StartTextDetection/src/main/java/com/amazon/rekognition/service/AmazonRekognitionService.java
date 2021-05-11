package com.amazon.rekognition.service;

import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    String startTextDetection(String fileName, String bucketName);
}