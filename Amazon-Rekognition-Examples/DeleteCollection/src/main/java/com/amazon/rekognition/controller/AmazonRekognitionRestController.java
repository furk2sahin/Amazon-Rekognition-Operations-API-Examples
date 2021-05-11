package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.DeleteCollectionResult;
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

    @DeleteMapping("/delete-collection")
    public ResponseEntity<DeleteCollectionResult> deleteCollectionResult(@RequestParam("collectionName") String collectionName){
        return ResponseEntity.ok(amazonRekognitionService.deleteCollection(collectionName));
    }
}