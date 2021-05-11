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
    public SearchFacesByImageResult searchFacesByImage(MultipartFile image, String collectionId) {
        SearchFacesByImageRequest request;
        SearchFacesByImageResult result;

        try{
            request = new SearchFacesByImageRequest()
                    .withQualityFilter(QualityFilter.AUTO)
                    .withCollectionId(collectionId)
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())))
                    .withFaceMatchThreshold(70F);
            result = amazonClient.searchFacesByImage(request);
        } catch (AmazonRekognitionException | IOException e){
            return null;
        }
        return result;
    }
}