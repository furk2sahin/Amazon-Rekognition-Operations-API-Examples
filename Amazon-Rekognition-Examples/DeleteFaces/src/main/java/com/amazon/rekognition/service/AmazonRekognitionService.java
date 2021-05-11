package com.amazon.rekognition.service;


import com.amazonaws.services.rekognition.model.DeleteFacesResult;
import org.springframework.stereotype.Service;

@Service
public interface AmazonRekognitionService {
    DeleteFacesResult deleteFaces(String collectionId, String faceId);
}