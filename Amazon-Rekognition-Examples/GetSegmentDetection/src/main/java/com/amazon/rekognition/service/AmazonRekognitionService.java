package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.GetSegmentDetectionResult;
import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    GetSegmentDetectionResult getSegmentDetection(String jobId);
}