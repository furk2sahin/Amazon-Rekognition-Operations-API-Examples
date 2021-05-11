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
    public RecognizeCelebritiesResult recognizeCelebrities(MultipartFile image) {
        RecognizeCelebritiesRequest request;
        RecognizeCelebritiesResult result;
        try{
            request = new RecognizeCelebritiesRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));
            result = amazonClient.recognizeCelebrities(request);
        } catch (AmazonRekognitionException | IOException e){
            return null;
        }

        return result;
    }
}
