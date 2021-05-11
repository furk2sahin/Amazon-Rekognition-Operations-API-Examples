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
    public UntagResourceResult untagResource(String resourceARN, String tagKey) {
        UntagResourceRequest request;
        UntagResourceResult result;
        try{
            request = new UntagResourceRequest()
                    .withResourceArn(resourceARN)
                    .withTagKeys(tagKey);
            result = amazonClient.untagResource(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

}
