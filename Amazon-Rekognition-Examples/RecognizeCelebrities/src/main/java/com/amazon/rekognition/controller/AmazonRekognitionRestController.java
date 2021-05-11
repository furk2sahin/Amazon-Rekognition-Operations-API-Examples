package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.RecognizeCelebritiesResult;
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

    @PostMapping(value = "/recognize-celebrities", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RecognizeCelebritiesResult> recognizeCelebritiesResult(@RequestParam("image") MultipartFile file) {
        return ResponseEntity.ok(amazonRekognitionService.recognizeCelebrities(file));
    }
}