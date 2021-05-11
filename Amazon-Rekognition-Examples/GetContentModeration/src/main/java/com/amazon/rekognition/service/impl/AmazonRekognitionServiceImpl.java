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
    public GetContentModerationResult getContentModeration(String jobId) {
        GetContentModerationRequest request = new GetContentModerationRequest()
                .withJobId(jobId)
                .withSortBy(ContentModerationSortBy.TIMESTAMP);
        GetContentModerationResult result;
        try{
             result = rekognitionClient.getContentModeration(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }
}
