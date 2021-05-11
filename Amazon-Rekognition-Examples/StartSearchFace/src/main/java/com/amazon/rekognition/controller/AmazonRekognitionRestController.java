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

    @PostMapping("/start-face-search")
    public ResponseEntity<String> startFaceSearchResult(@RequestParam("fileName") String fileName,
                                             @RequestParam("bucketName") String bucketName,
                                                            @RequestParam("collectionId") String collectionId) {
        return ResponseEntity.ok(amazonRekognitionService.startFaceSearch(fileName, bucketName, collectionId));
    }
}