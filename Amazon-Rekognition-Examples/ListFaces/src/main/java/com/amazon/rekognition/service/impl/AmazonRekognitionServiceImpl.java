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
    public List<Face> listFaces(String collectionId) {
        ListFacesRequest request = new ListFacesRequest()
                .withCollectionId(collectionId);

        ListFacesResult result;
        List<Face> faces;
        try{
            result = amazonClient.listFaces(request);
            faces = result.getFaces();
        } catch (AmazonRekognitionException e){
            return Collections.emptyList();
        }
        return faces;
    }
}
