package com.amazon.rekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazon.rekognition.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
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
    public List<Label> detectLabels(MultipartFile image) {
        DetectLabelsRequest request;
        DetectLabelsResult detectLabelsResult;
        List<Label> labels;
        try{
            request = new DetectLabelsRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));
            detectLabelsResult = amazonClient.detectLabels(request);
            labels = detectLabelsResult.getLabels();
        } catch (AmazonRekognitionException | IOException e){
            return Collections.emptyList();
        }

        return labels;
    }

}
