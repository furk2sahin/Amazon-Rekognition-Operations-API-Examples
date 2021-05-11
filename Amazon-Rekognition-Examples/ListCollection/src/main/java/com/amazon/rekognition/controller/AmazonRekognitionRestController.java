package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AmazonRekognitionRestController {

    private AmazonRekognitionService amazonRekognitionService;

    @Autowired
    public AmazonRekognitionRestController(AmazonRekognitionService amazonRekognitionService) {
        this.amazonRekognitionService = amazonRekognitionService;
    }

    @GetMapping("/list-collections")
    public ResponseEntity<List<String>> listCollectionResult(){
        return ResponseEntity.ok(amazonRekognitionService.listCollections());
    }
}