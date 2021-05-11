package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.GetContentModerationResult;
import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    GetContentModerationResult getContentModeration(String jobId);
}