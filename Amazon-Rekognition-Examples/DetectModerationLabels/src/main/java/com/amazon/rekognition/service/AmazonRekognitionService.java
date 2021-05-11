package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AmazonRekognitionService {
    DetectModerationLabelsResult detectModerationLabels(MultipartFile image);
}