package com.amazon.rekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazon.rekognition.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AmazonRekognitionServiceImpl implements AmazonRekognitionService {

    private AmazonRekognition amazonClient;

    @Autowired
    public void setAmazonClient(AmazonRekognition amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Override
    public List<String> listCollections() {
        ListCollectionsRequest request = new ListCollectionsRequest()
                .withMaxResults(100);
        List<String> collectionIds;
        try{
            ListCollectionsResult result = amazonClient.listCollections(request);
            collectionIds = result.getCollectionIds();
        } catch (AmazonRekognitionException e){
            return Collections.emptyList();
        }
        return collectionIds;
    }
}
