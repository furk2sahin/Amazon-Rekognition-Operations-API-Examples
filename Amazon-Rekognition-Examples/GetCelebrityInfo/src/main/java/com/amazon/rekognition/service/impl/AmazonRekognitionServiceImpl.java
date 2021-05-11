package com.amazon.rekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazon.rekognition.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazonRekognitionServiceImpl implements AmazonRekognitionService {

    private AmazonRekognition amazonClient;

    @Autowired
    public void setAmazonClient(AmazonRekognition amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Override
    public GetCelebrityInfoResult getCelebrityInfo(String rekognitionId) {
        GetCelebrityInfoRequest request;
        GetCelebrityInfoResult result;
        try{
            request = new GetCelebrityInfoRequest()
                    .withId(rekognitionId);
            result = amazonClient.getCelebrityInfo(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

}
