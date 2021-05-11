package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.IndexFacesResult;
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

    @PostMapping(value = "/index-faces", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<IndexFacesResult> indexFacesResult(@RequestParam("image") MultipartFile image, @RequestParam("collectionId") String collectionId) {
        return ResponseEntity.ok(amazonRekognitionService.indexFaces(image, collectionId));
    }
}