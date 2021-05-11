package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AmazonRekognitionService {
    IndexFacesResult indexFaces(MultipartFile image, String collectionId);
}