package com.amazon.rekognition.service;

import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    String startPersonTracking(String fileName, String bucketName);
}