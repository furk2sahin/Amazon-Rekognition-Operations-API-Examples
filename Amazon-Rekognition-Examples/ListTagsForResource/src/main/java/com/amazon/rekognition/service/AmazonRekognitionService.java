package com.amazon.rekognition.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AmazonRekognitionService {
    Map<String, String> listTagsForResource(String resourceARN);
}