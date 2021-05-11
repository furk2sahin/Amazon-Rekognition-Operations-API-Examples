package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AmazonRekognitionService {
    List<Face> listFaces(String collectionId);
}