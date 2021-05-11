package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface AmazonRekognitionService {
    List<TextDetection> detectTexts(MultipartFile image);
}