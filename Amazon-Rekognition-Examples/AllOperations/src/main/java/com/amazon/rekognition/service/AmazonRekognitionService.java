package com.amazon.rekognition.service;

import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface AmazonRekognitionService {
    List<Label> detectLabels(MultipartFile image);
    List<FaceDetail> detectFaces(MultipartFile image);
    List<TextDetection> detectTexts(MultipartFile image);
    String createCollection(String collectionName);
    DescribeCollectionResult describeCollection(String collectionId);
    List<String> listCollections();
    DeleteCollectionResult deleteCollection(String collectionName);
    IndexFacesResult indexFaces(MultipartFile image, String collectionId);
    List<Face> listFaces(String collectionId);
    SearchFacesResult searchFaces(String collectionId, String faceId);
    SearchFacesByImageResult searchFacesByImage(MultipartFile image, String collectionId);
    DeleteFacesResult deleteFaces(String collectionId, String faceId);
    CompareFacesResult compareFaces(MultipartFile sourceImage, MultipartFile targetImage);
    RecognizeCelebritiesResult recognizeCelebrities(MultipartFile image);
    DetectProtectiveEquipmentResult detectProtectiveEquipment(MultipartFile image);
    DetectModerationLabelsResult detectModerationLabels(MultipartFile image);
    GetCelebrityInfoResult getCelebrityInfo(String rekognitionId);
    TagResourceResult tagResource(String resourceARN, Map<String, String> tags);
    UntagResourceResult untagResource(String resourceARN, String tagKey);
    Map<String, String> listTagsForResource(String resourceARN);
    byte[] drawBoundingBox(MultipartFile multipartFile);
    String startLabelDetection(String fileName, String bucketName);
    GetLabelDetectionResult getLabelDetection(String jobId);
    String startCelebrityRecognition(String fileName, String bucketName);
    GetCelebrityRecognitionResult getCelebrityRecognition(String jobId);
    String startFaceDetection(String fileName, String bucketName);
    GetFaceDetectionResult getFaceDetection(String jobId);
    String startTextDetection(String fileName, String bucketName);
    GetTextDetectionResult getTextDetection(String jobId);
    String startContentModeration(String fileName, String bucketName);
    GetContentModerationResult getContentModeration(String jobId);
    String startSegmentDetection(String fileName, String bucketName);
    GetSegmentDetectionResult getSegmentDetection(String jobId);
    String startPersonTracking(String fileName, String bucketName);
    GetPersonTrackingResult getPersonTracking(String jobId);
    String startFaceSearch(String fileName, String bucketName, String collectionId);
    GetFaceSearchResult getFaceSearch(String jobId);
    String uploadFile(MultipartFile file, String bucketName);
}