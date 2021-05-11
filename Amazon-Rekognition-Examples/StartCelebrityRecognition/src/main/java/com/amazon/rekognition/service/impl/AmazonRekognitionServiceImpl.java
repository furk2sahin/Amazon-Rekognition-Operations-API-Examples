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
    public String startCelebrityRecognition(String fileName, String bucketName) {
        S3Object s3Object = new S3Object().withBucket(bucketName).withName(fileName);
        Video video = new Video().withS3Object(s3Object);
        StartCelebrityRecognitionRequest request = new StartCelebrityRecognitionRequest()
                .withVideo(video);
        StartCelebrityRecognitionResult result;
        String jobId;
        try{
            result = rekognitionClient.startCelebrityRecognition(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartCelebrityRecognition'";
        }
        return jobId;
    }
}
