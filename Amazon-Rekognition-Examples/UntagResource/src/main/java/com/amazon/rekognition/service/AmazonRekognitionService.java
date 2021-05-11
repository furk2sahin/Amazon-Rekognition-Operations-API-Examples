package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    UntagResourceResult untagResource(String resourceARN, String tagKey);
}