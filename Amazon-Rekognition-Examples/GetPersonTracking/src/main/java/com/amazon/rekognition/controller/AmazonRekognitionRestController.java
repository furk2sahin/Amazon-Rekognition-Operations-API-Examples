package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.GetPersonTrackingResult;
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

    @PostMapping("/get-person-tracking")
    public ResponseEntity<GetPersonTrackingResult> getPersonTrackingResult(@RequestParam("jobId") String jobId) {
        return ResponseEntity.ok(amazonRekognitionService.getPersonTracking(jobId));
    }
}