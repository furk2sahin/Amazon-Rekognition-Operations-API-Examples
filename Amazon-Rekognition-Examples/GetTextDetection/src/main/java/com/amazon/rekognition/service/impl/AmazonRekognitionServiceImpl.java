package com.amazon.rekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazon.rekognition.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazonRekognitionServiceImpl implements AmazonRekognitionService {

    private AmazonRekognition rekognitionClient;

    @Autowired
    public void setRekognitionClient(AmazonRekognition rekognitionClient) {
        this.rekognitionClient = rekognitionClient;
    }

    @Override
    public GetTextDetectionResult getTextDetection(String jobId) {
        GetTextDetectionRequest request = new GetTextDetectionRequest()
                .withJobId(jobId);
        GetTextDetectionResult result;
        try{
             result = rekognitionClient.getTextDetection(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }
}
