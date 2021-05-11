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
    public CompareFacesResult compareFaces(MultipartFile sourceImage, MultipartFile targetImage) {
        CompareFacesRequest request;
        CompareFacesResult result;

        try{
            request = new CompareFacesRequest()
                    .withQualityFilter(QualityFilter.AUTO)
                    .withSimilarityThreshold(70F)
                    .withSourceImage(new Image().withBytes(ByteBuffer.wrap(sourceImage.getBytes())))
                    .withTargetImage(new Image().withBytes(ByteBuffer.wrap(targetImage.getBytes())));
            result = amazonClient.compareFaces(request);
        } catch (IOException|AmazonRekognitionException e){
            return null;
        }
        return result;
    }
}
