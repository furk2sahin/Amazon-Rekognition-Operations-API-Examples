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
    public TagResourceResult tagResource(String resourceARN, Map<String, String> tags) {
        TagResourceRequest request;
        TagResourceResult result;
        try{
            request = new TagResourceRequest()
                    .withResourceArn(resourceARN)
                    .withTags(tags);
            result = amazonClient.tagResource(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

}
