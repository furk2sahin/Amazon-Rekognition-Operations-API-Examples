package com.amazon.rekognition.service;

import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    String startFaceSearch(String fileName, String bucketName, String collectionId);
}