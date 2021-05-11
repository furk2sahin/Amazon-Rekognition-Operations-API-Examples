package com.amazon.rekognition.controller;

import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AmazonRekognitionRestController {

    private AmazonRekognitionService amazonRekognitionService;

    @Autowired
    public AmazonRekognitionRestController(AmazonRekognitionService amazonRekognitionService) {
        this.amazonRekognitionService = amazonRekognitionService;
    }

    @PostMapping(value = "/detect-labels", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Label>> detectLabelsResult(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(amazonRekognitionService.detectLabels(image));
    }

    @PostMapping(value = "/detect-faces", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<FaceDetail>> detectFacesResult(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(amazonRekognitionService.detectFaces(image));
    }

    @PostMapping(value = "/detect-texts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<TextDetection>> detectTextsResult(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(amazonRekognitionService.detectTexts(image));
    }

    @PostMapping("/create-collection")
    public ResponseEntity<String> createCollectionResult(@RequestParam("collectionName") String collectionName) {
        return ResponseEntity.ok(amazonRekognitionService.createCollection(collectionName));
    }

    @GetMapping("/describe-collection")
    public ResponseEntity<DescribeCollectionResult> describeCollectionResult(@RequestParam("collectionId") String collectionId){
        return ResponseEntity.ok(amazonRekognitionService.describeCollection(collectionId));
    }

    @GetMapping("/list-collections")
    public ResponseEntity<List<String>> listCollectionResult(){
        return ResponseEntity.ok(amazonRekognitionService.listCollections());
    }

    @DeleteMapping("/delete-collection")
    public ResponseEntity<DeleteCollectionResult> deleteCollectionResult(@RequestParam("collectionName") String collectionName){
        return ResponseEntity.ok(amazonRekognitionService.deleteCollection(collectionName));
    }

    @PostMapping(value = "/index-faces", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<IndexFacesResult> indexFacesResult(@RequestParam("image") MultipartFile image, @RequestParam("collectionId") String collectionId) {
        return ResponseEntity.ok(amazonRekognitionService.indexFaces(image, collectionId));
    }

    @GetMapping("/list-faces")
    public ResponseEntity<List<Face>> listFacesResult(@RequestParam("collectionId") String collectionId) {
        return ResponseEntity.ok(amazonRekognitionService.listFaces(collectionId));
    }

    @GetMapping("/search-faces")
    public ResponseEntity<SearchFacesResult> searchFacesResult(@RequestParam("collectionId") String collectionId,
                                                               @RequestParam("faceId") String faceId) {
        return ResponseEntity.ok(amazonRekognitionService.searchFaces(collectionId, faceId));
    }

    @PostMapping(value = "/search-faces-by-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SearchFacesByImageResult> searchFacesByImageResult(@RequestParam("image") MultipartFile image,
                                                                             @RequestParam("collectionId") String collectionId) {
        return ResponseEntity.ok(amazonRekognitionService.searchFacesByImage(image, collectionId));
    }

    @DeleteMapping("/delete-faces")
    public ResponseEntity<DeleteFacesResult> deleteFacesResult(@RequestParam("collectionId") String collectionId,
                                                               @RequestParam("faceId") String faceId) {
        return ResponseEntity.ok(amazonRekognitionService.deleteFaces(collectionId, faceId));
    }

    @PostMapping(value = "/compare-faces", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CompareFacesResult> compareFaces(@RequestParam("sourceImage") MultipartFile sourceImage, @RequestParam("targetImage") MultipartFile targetImage) {
        return ResponseEntity.ok(amazonRekognitionService.compareFaces(sourceImage, targetImage));
    }

    @PostMapping(value = "/recognize-celebrities", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RecognizeCelebritiesResult> recognizeCelebritiesResult(@RequestParam("image") MultipartFile file) {
        return ResponseEntity.ok(amazonRekognitionService.recognizeCelebrities(file));
    }

    @PostMapping(value = "/detect-protective-equipments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DetectProtectiveEquipmentResult> detectProtectiveEquipmentResult(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(amazonRekognitionService.detectProtectiveEquipment(image));
    }

    @PostMapping(value = "/detect-moderation-labels", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DetectModerationLabelsResult> detectModerationLabelsResult(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(amazonRekognitionService.detectModerationLabels(image));
    }

    @GetMapping("/get-celebrity-info")
    public ResponseEntity<GetCelebrityInfoResult> getCelebrityInfoResult(@RequestParam("rekognitionId") String rekognitionId) {
        return ResponseEntity.ok(amazonRekognitionService.getCelebrityInfo(rekognitionId));
    }

    @PutMapping("/tag-resource")
    public ResponseEntity<TagResourceResult> tagResourceResult(@RequestParam("resourceARN") String resourceARN,
                                                               @RequestParam("key") String key,
                                                               @RequestParam("value") String value) {
        Map<String, String> tags = new HashMap<>();
        tags.put(key, value);
        return ResponseEntity.ok(amazonRekognitionService.tagResource(resourceARN, tags));
    }

    @PutMapping("/untag-resource")
    public ResponseEntity<UntagResourceResult> untagResourceResult(@RequestParam("resourceARN") String resourceARN,
                                                                   @RequestParam("key") String key) {
        return ResponseEntity.ok(amazonRekognitionService.untagResource(resourceARN, key));
    }

    @GetMapping("/list-tags-for-resource")
    public ResponseEntity<Map<String, String>> listTagsForResourceResult(@RequestParam("resourceARN") String resourceARN) {
        return ResponseEntity.ok(amazonRekognitionService.listTagsForResource(resourceARN));
    }

    @PostMapping(value = "/draw-bounding-boxes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> drawBoundingBoxes(@RequestParam("image") MultipartFile file) {
        return ResponseEntity.ok(amazonRekognitionService.drawBoundingBox(file));
    }

    @PostMapping("/start-label-detection")
    public ResponseEntity<String> startLabelDetectionResult(@RequestParam("fileName") String fileName,
                                                            @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.startLabelDetection(fileName, bucketName));
    }

    @PostMapping("/get-label-detection")
    public ResponseEntity<GetLabelDetectionResult> getLabelDetectionResult(@RequestParam("jobId") String jobId) {
        return ResponseEntity.ok(amazonRekognitionService.getLabelDetection(jobId));
    }

    @PostMapping("/start-celebrity-detection")
    public ResponseEntity<String> startCelebrityDetectionResult(@RequestParam("fileName") String fileName,
                                                                @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.startCelebrityRecognition(fileName, bucketName));
    }

    @PostMapping("/get-celebrity-detection")
    public ResponseEntity<GetCelebrityRecognitionResult> getCelebrityDetectionResult(@RequestParam("jobId") String jobId) {
        return ResponseEntity.ok(amazonRekognitionService.getCelebrityRecognition(jobId));
    }

    @PostMapping("/start-face-detection")
    public ResponseEntity<String> startFaceDetectionResult(@RequestParam("fileName") String fileName,
                                                           @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.startFaceDetection(fileName, bucketName));
    }

    @PostMapping("/get-face-detection")
    public ResponseEntity<GetFaceDetectionResult> getFaceDetectionResult(@RequestParam("jobId") String jobId) {
        return ResponseEntity.ok(amazonRekognitionService.getFaceDetection(jobId));
    }

    @PostMapping("/start-text-detection")
    public ResponseEntity<String> startTextDetectionResult(@RequestParam("fileName") String fileName,
                                                           @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.startTextDetection(fileName, bucketName));
    }

    @PostMapping("/get-text-detection")
    public ResponseEntity<GetTextDetectionResult> getTextDetectionResult(@RequestParam("jobId") String jobId) {
        return ResponseEntity.ok(amazonRekognitionService.getTextDetection(jobId));
    }

    @PostMapping("/start-content-moderation")
    public ResponseEntity<String> startContentModerationResult(@RequestParam("fileName") String fileName,
                                                               @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.startContentModeration(fileName, bucketName));
    }

    @PostMapping("/get-content-moderation")
    public ResponseEntity<GetContentModerationResult> getContentModerationResult(@RequestParam("jobId") String jobId) {
        return ResponseEntity.ok(amazonRekognitionService.getContentModeration(jobId));
    }

    @PostMapping("/start-segment-detection")
    public ResponseEntity<String> startSegmentDetectionResult(@RequestParam("fileName") String fileName,
                                                              @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.startSegmentDetection(fileName, bucketName));
    }

    @PostMapping("/get-segment-detection")
    public ResponseEntity<GetSegmentDetectionResult> getSegmentDetectionResult(@RequestParam("jobId") String jobId) {
        return ResponseEntity.ok(amazonRekognitionService.getSegmentDetection(jobId));
    }

    @PostMapping("/start-person-tracking")
    public ResponseEntity<String> startPersonTrackingResult(@RequestParam("fileName") String fileName,
                                                            @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.startPersonTracking(fileName, bucketName));
    }

    @PostMapping("/get-person-tracking")
    public ResponseEntity<GetPersonTrackingResult> getPersonTrackingResult(@RequestParam("jobId") String jobId) {
        return ResponseEntity.ok(amazonRekognitionService.getPersonTracking(jobId));
    }

    @PostMapping("/start-face-search")
    public ResponseEntity<String> startFaceSearchResult(@RequestParam("fileName") String fileName,
                                                        @RequestParam("bucketName") String bucketName,
                                                        @RequestParam("collectionId") String collectionId) {
        return ResponseEntity.ok(amazonRekognitionService.startFaceSearch(fileName, bucketName, collectionId));
    }

    @PostMapping("/get-face-search")
    public ResponseEntity<GetFaceSearchResult> getFaceSearchResult(@RequestParam("jobId") String jobId) {
        return ResponseEntity.ok(amazonRekognitionService.getFaceSearch(jobId));
    }

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("bucketName") String bucketName) {
        return ResponseEntity.ok(amazonRekognitionService.uploadFile(file, bucketName));
    }

}