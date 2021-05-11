package com.amazon.rekognition.service;

import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    String startFaceDetection(String fileName, String bucketName);
}