package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.GetFaceDetectionResult;
import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    GetFaceDetectionResult getFaceDetection(String jobId);
}