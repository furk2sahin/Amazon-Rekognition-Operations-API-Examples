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
    public SearchFacesResult searchFaces(String collectionId, String faceId){
        SearchFacesRequest request;
        SearchFacesResult result;

        try{
            request = new SearchFacesRequest()
                    .withCollectionId(collectionId)
                    .withFaceId(faceId)
                    .withFaceMatchThreshold(70F);
            result = amazonClient.searchFaces(request);
        } catch (AmazonRekognitionException e){
            return null;
        }
        return result;
    }
}
