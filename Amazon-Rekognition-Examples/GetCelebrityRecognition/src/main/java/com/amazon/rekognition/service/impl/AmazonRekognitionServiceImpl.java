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
    public GetCelebrityRecognitionResult getCelebrityRecognition(String jobId) {
        GetCelebrityRecognitionRequest request = new GetCelebrityRecognitionRequest()
                .withJobId(jobId)
                .withSortBy(CelebrityRecognitionSortBy.TIMESTAMP);
        GetCelebrityRecognitionResult result;

        try{
            result = rekognitionClient.getCelebrityRecognition(request);
        } catch (AmazonRekognitionException e){
            return null;
        }
        return result;
    }
}
