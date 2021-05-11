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
    public String startFaceDetection(String fileName, String bucketName) {
        S3Object s3Object = new S3Object().withBucket(bucketName).withName(fileName);
        Video video = new Video().withS3Object(s3Object);
        StartFaceDetectionRequest request = new StartFaceDetectionRequest()
                .withVideo(video);
        String jobId;
        try{
            StartFaceDetectionResult result = rekognitionClient.startFaceDetection(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartFaceDetection'";
        }

        return jobId;
    }
}
