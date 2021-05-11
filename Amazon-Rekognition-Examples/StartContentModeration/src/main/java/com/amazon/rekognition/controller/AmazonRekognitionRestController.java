package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
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

    @PostMapping("/start-content-moderation")
    public ResponseEntity<String> startContentModerationResult(@RequestParam("fileName") String fileName,
                                             @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.startContentModeration(fileName, bucketName));
    }
}