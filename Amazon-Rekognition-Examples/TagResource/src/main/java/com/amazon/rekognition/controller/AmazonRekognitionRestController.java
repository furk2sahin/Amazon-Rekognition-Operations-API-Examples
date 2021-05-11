package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.TagResourceResult;
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

    @PutMapping("/tag-resource")
    public ResponseEntity<TagResourceResult> tagResourceResult(@RequestParam("resourceARN") String resourceARN,
                                                          @RequestParam("key") String key,
                                                          @RequestParam("value") String value) {
        Map<String, String> tags = new HashMap<>();
        tags.put(key, value);
        return ResponseEntity.ok(amazonRekognitionService.tagResource(resourceARN, tags));
    }
}