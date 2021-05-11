package com.amazon.rekognition.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AmazonRekognitionService {
    List<String> listCollections();
}