package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AmazonRekognitionService {
    TagResourceResult tagResource(String resourceARN, Map<String, String> tags);
}