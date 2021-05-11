package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.GetLabelDetectionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AmazonRekognitionRestController {

    private AmazonRekognitionService amazonRekognitionService;

    @Autowired
    public AmazonRekognitionRestController(AmazonRekognitionService amazonRekognitionService) {
        this.amazonRekognitionService = amazonRekognitionService;
    }

    @PostMapping("/start-text-detection")
    public ResponseEntity<String> startTextDetectionResult(@RequestParam("fileName") String fileName,
                                             @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.startTextDetection(fileName, bucketName));
    }
}