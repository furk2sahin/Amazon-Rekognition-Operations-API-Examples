package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.UntagResourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AmazonRekognitionRestController {

    private AmazonRekognitionService amazonRekognitionService;

    @Autowired
    public AmazonRekognitionRestController(AmazonRekognitionService amazonRekognitionService) {
        this.amazonRekognitionService = amazonRekognitionService;
    }

    @PutMapping("/untag-resource")
    public ResponseEntity<UntagResourceResult> untagResourceResult(@RequestParam("resourceARN") String resourceARN,
                                                            @RequestParam("key") String key) {
        return ResponseEntity.ok(amazonRekognitionService.untagResource(resourceARN, key));
    }
}