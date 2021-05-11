package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.DescribeCollectionResult;
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

    @GetMapping("/describe-collection")
    public ResponseEntity<DescribeCollectionResult> describeCollectionResult(@RequestParam("collectionId") String collectionId){
        return ResponseEntity.ok(amazonRekognitionService.describeCollection(collectionId));
    }
}