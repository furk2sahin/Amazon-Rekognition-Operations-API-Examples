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
    public GetFaceSearchResult getFaceSearch(String jobId) {
        GetFaceSearchRequest request = new GetFaceSearchRequest()
                .withJobId(jobId)
                .withSortBy(FaceSearchSortBy.TIMESTAMP);
        GetFaceSearchResult result;
        try{
             result = rekognitionClient.getFaceSearch(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }
}
