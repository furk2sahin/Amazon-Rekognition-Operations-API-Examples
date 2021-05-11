package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.DetectProtectiveEquipmentResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AmazonRekognitionService {
    DetectProtectiveEquipmentResult detectProtectiveEquipment(MultipartFile image);
}