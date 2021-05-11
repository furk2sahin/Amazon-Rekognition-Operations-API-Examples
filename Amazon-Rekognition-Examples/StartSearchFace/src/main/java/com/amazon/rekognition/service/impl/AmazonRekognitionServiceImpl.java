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
    public String startFaceSearch(String fileName, String bucketName, String collectionId) {
        S3Object s3Object = new S3Object().withBucket(bucketName).withName(fileName);
        Video video = new Video().withS3Object(s3Object);
        StartFaceSearchRequest request = new StartFaceSearchRequest()
                .withVideo(video)
                .withCollectionId(collectionId)
                .withFaceMatchThreshold(70F);
        String jobId;
        try{
            StartFaceSearchResult result = rekognitionClient.startFaceSearch(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartFaceSearch";
        }
        return jobId;
    }
}
