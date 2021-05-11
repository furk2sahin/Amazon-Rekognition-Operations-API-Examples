package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.GetPersonTrackingResult;
import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    GetPersonTrackingResult getPersonTracking(String jobId);
}