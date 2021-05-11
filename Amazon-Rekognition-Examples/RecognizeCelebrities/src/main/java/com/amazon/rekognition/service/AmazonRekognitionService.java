package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.RecognizeCelebritiesResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AmazonRekognitionService {
    RecognizeCelebritiesResult recognizeCelebrities(MultipartFile image);
}