package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.SearchFacesResult;
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

    @GetMapping("/search-faces")
    public ResponseEntity<SearchFacesResult> searchFacesResult(@RequestParam("collectionId") String collectionId,
                                                         @RequestParam("faceId") String faceId) {
        return ResponseEntity.ok(amazonRekognitionService.searchFaces(collectionId, faceId));
    }
}