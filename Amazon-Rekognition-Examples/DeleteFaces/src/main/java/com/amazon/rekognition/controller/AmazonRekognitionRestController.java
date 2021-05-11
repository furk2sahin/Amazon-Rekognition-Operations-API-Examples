package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.DeleteFacesResult;
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

    @DeleteMapping("/delete-faces")
    public ResponseEntity<DeleteFacesResult> deleteFacesResult(@RequestParam("collectionId") String collectionId,
                                                               @RequestParam("faceId") String faceId) {
        return ResponseEntity.ok(amazonRekognitionService.deleteFaces(collectionId, faceId));
    }
}