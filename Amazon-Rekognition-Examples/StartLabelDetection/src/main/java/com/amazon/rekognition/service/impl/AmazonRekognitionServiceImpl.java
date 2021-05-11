package com.amazon.rekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazon.rekognition.service.AmazonRekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazonRekognitionServiceImpl implements AmazonRekognitionService {

    private AmazonRekognition rekognitionClient;

    @Autowired
    public void setRekognitionClient(AmazonRekognition rekognitionClient) {
        this.rekognitionClient = rekognitionClient;
    }

    @Override
    public String startLabelDetection(String fileName, String bucketName) {
        Video video = new Video().withS3Object(new S3Object()
                .withBucket(bucketName)
                .withName(fileName));
        StartLabelDetectionRequest request = new StartLabelDetectionRequest()
                .withVideo(video)
                .withMinConfidence(70F);
        StartLabelDetectionResult result;
        String jobId;
        try{
            result = rekognitionClient.startLabelDetection(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return null;
        }

        return jobId;
    }
}
