package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.GetTextDetectionResult;
import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    GetTextDetectionResult getTextDetection(String jobId);
}