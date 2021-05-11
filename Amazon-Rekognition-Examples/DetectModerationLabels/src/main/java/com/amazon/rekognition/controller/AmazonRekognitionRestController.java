package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AmazonRekognitionRestController {

    private AmazonRekognitionService amazonRekognitionService;

    @Autowired
    public AmazonRekognitionRestController(AmazonRekognitionService amazonRekognitionService) {
        this.amazonRekognitionService = amazonRekognitionService;
    }

    @PostMapping(value = "/detect-moderation-labels", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DetectModerationLabelsResult> detectModerationLabelsResult(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(amazonRekognitionService.detectModerationLabels(image));
    }
}