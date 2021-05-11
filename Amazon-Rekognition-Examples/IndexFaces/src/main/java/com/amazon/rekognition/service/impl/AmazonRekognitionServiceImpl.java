package com.amazon.rekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazon.rekognition.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
@Service
public class AmazonRekognitionServiceImpl implements AmazonRekognitionService {

    private AmazonRekognition amazonClient;

    @Autowired
    public void setAmazonClient(AmazonRekognition amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Override
    public IndexFacesResult indexFaces(MultipartFile image, String collectionId){
        IndexFacesRequest request;
        IndexFacesResult result;
        try{
            request= new IndexFacesRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())))
                    .withCollectionId(collectionId)
                    .withExternalImageId(image.getOriginalFilename())
                    .withDetectionAttributes("ALL");
            result = amazonClient.indexFaces(request);
        } catch (AmazonRekognitionException | IOException e){
            return null;
        }

        return result;
    }
}
