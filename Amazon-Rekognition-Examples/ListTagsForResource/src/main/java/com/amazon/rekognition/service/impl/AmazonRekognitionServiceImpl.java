package com.amazon.rekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazon.rekognition.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AmazonRekognitionServiceImpl implements AmazonRekognitionService {

    private AmazonRekognition amazonClient;

    @Autowired
    public void setAmazonClient(AmazonRekognition amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Override
    public Map<String, String> listTagsForResource(String resourceARN) {
        ListTagsForResourceRequest request;
        ListTagsForResourceResult result;
        Map<String, String> tags;
        try{
            request = new ListTagsForResourceRequest()
                    .withResourceArn(resourceARN);
            result = amazonClient.listTagsForResource(request);
            tags = result.getTags();
        } catch (AmazonRekognitionException e){
            return null;
        }

        return tags;
    }

}
