package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.GetFaceSearchResult;
import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    GetFaceSearchResult getFaceSearch(String jobId);
}