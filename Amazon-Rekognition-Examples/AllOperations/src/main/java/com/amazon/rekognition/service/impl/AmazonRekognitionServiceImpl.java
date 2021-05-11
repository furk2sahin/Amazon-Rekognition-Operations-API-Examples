package com.amazon.rekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.amazon.rekognition.service.AmazonRekognitionService;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class AmazonRekognitionServiceImpl implements AmazonRekognitionService {

    private AmazonRekognition amazonClient;
    private AmazonS3 amazonS3Client;

    @Autowired
    public void setAmazonS3Client(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

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

    @Override
    public List<FaceDetail> detectFaces(MultipartFile image){
        DetectFacesRequest request;
        DetectFacesResult result;
        List<FaceDetail> faceDetails;
        try{
            request = new DetectFacesRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())))
                    .withAttributes(Attribute.ALL);
            result = amazonClient.detectFaces(request);
            faceDetails = result.getFaceDetails();
        } catch (AmazonRekognitionException | IOException e) {
            return Collections.emptyList();
        }
        return faceDetails;
    }

    @Override
    public List<TextDetection> detectTexts(MultipartFile image) {
        DetectTextRequest request;
        DetectTextResult detectTextResult;
        List<TextDetection> textDetections;

        try{
            request = new DetectTextRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));
            detectTextResult = amazonClient.detectText(request);
            textDetections = detectTextResult.getTextDetections();
        }catch (AmazonRekognitionException | IOException e){
            return Collections.emptyList();
        }
        return textDetections;
    }

    @Override
    public String createCollection(String collectionName) {
        CreateCollectionRequest request = new CreateCollectionRequest()
                .withCollectionId(collectionName);
        CreateCollectionResult result;
        String collectionArn;
        try{
            result = amazonClient.createCollection(request);
            collectionArn = result.getCollectionArn();
        } catch (AmazonRekognitionException e){
            return null;
        }
        return collectionArn;
    }

    @Override
    public DescribeCollectionResult describeCollection(String collectionId) {
        DescribeCollectionRequest request = new DescribeCollectionRequest()
                .withCollectionId(collectionId);

        DescribeCollectionResult describeCollectionResult;

        try{
            describeCollectionResult = amazonClient.describeCollection(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return describeCollectionResult;
    }

    @Override
    public List<String> listCollections() {
        ListCollectionsRequest request = new ListCollectionsRequest()
                .withMaxResults(100);
        List<String> collectionIds;
        try{
            ListCollectionsResult result = amazonClient.listCollections(request);
            collectionIds = result.getCollectionIds();
        } catch (AmazonRekognitionException e){
            return Collections.emptyList();
        }
        return collectionIds;
    }

    @Override
    public DeleteCollectionResult deleteCollection(String collectionName) {
        DeleteCollectionRequest request = new DeleteCollectionRequest()
                .withCollectionId(collectionName);

        DeleteCollectionResult  result;

        try{
            result = amazonClient.deleteCollection(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

    @Override
    public IndexFacesResult indexFaces(MultipartFile image, String collectionId){
        IndexFacesRequest request;
        IndexFacesResult result;
        try{
            request= new IndexFacesRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())))
                    .withCollectionId(collectionId)
                    .withExternalImageId(image.getOriginalFilename())
                    .withDetectionAttributes("ALL");
            result = amazonClient.indexFaces(request);
        } catch (AmazonRekognitionException | IOException e){
            return null;
        }

        return result;
    }

    @Override
    public List<Face> listFaces(String collectionId) {
        ListFacesRequest request = new ListFacesRequest()
                .withCollectionId(collectionId);

        ListFacesResult result;
        List<Face> faces;
        try{
            result = amazonClient.listFaces(request);
            faces = result.getFaces();
        } catch (AmazonRekognitionException e){
            return Collections.emptyList();
        }
        return faces;
    }

    @Override
    public SearchFacesResult searchFaces(String collectionId, String faceId){
        SearchFacesRequest request;
        SearchFacesResult result;

        try{
            request = new SearchFacesRequest()
                    .withCollectionId(collectionId)
                    .withFaceId(faceId)
                    .withFaceMatchThreshold(70F);
            result = amazonClient.searchFaces(request);
        } catch (AmazonRekognitionException e){
            return null;
        }
        return result;
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

    @Override
    public DeleteFacesResult deleteFaces(String collectionId, String faceId){
        String[] faces = {faceId};
        DeleteFacesRequest request = new DeleteFacesRequest()
                .withCollectionId(collectionId)
                .withFaceIds(faces);

        DeleteFacesResult result;
        try{
            result = amazonClient.deleteFaces(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
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

    @Override
    public DetectProtectiveEquipmentResult detectProtectiveEquipment(MultipartFile image) {
        DetectProtectiveEquipmentRequest request;
        DetectProtectiveEquipmentResult result;

        try{
            request = new DetectProtectiveEquipmentRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));
            result = amazonClient.detectProtectiveEquipment(request);
        } catch (AmazonRekognitionException | IOException e){
            return null;
        }
        return result;
    }

    @Override
    public DetectModerationLabelsResult detectModerationLabels(MultipartFile image) {
        DetectModerationLabelsRequest request;
        DetectModerationLabelsResult result;
        try{
            request = new DetectModerationLabelsRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(image.getBytes())));
            result = amazonClient.detectModerationLabels(request);
        } catch (AmazonRekognitionException | IOException e){
            return null;
        }

        return result;
    }

    @Override
    public GetCelebrityInfoResult getCelebrityInfo(String rekognitionId) {
        GetCelebrityInfoRequest request;
        GetCelebrityInfoResult result;
        try{
            request = new GetCelebrityInfoRequest()
                    .withId(rekognitionId);
            result = amazonClient.getCelebrityInfo(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

    @Override
    public TagResourceResult tagResource(String resourceARN, Map<String, String> tags) {
        TagResourceRequest request;
        TagResourceResult result;
        try{
            request = new TagResourceRequest()
                    .withResourceArn(resourceARN)
                    .withTags(tags);
            result = amazonClient.tagResource(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

    @Override
    public UntagResourceResult untagResource(String resourceARN, String tagKey) {
        UntagResourceRequest request;
        UntagResourceResult result;
        try{
            request = new UntagResourceRequest()
                    .withResourceArn(resourceARN)
                    .withTagKeys(tagKey);
            result = amazonClient.untagResource(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

    @Override
    public Map<String, String> listTagsForResource(String resourceARN) {
        ListTagsForResourceRequest request;
        ListTagsForResourceResult result;
        Map<String, String> tags;
        try{
            request = new ListTagsForResourceRequest()
                    .withResourceArn(resourceARN);
            result = amazonClient.listTagsForResource(request);
            tags = result.getTags();
        } catch (AmazonRekognitionException e){
            return null;
        }

        return tags;
    }

    @Override
    public byte[] drawBoundingBox(MultipartFile multipartFile){
        ByteBuffer imageBytes = null;

        try(InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes())){
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        InputStream imageBytesStream = new ByteArrayInputStream(imageBytes.array());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageBytesStream);
            ImageIO.write(image, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        int width = image.getWidth();
        int height = image.getHeight();

        List<FaceDetail> faceDetails = getFaceDetails(multipartFile);

        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(Color.red);
        graphics2D.setStroke(new BasicStroke(3.0f));

        for(FaceDetail face : faceDetails){
            BoundingBox boundingBox = face.getBoundingBox();
            graphics2D.drawRect((int)(boundingBox.getLeft() * width), (int)(boundingBox.getTop() * height),
                    (int)(boundingBox.getWidth() * width), (int)(boundingBox.getHeight() * height));
        }

        graphics2D.dispose();

        ByteArrayOutputStream drawedImageBaos = new ByteArrayOutputStream();
        byte[] encodedStr = null;
        try {
            ImageIO.write(image, "jpg",
                    drawedImageBaos);
            byte[] drawedImageBytes = drawedImageBaos.toByteArray();
            encodedStr = Base64.getEncoder().encode(drawedImageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedStr;
    }

    public List<FaceDetail> getFaceDetails(MultipartFile multipartFile){
        DetectFacesRequest request;
        DetectFacesResult detectFacesResult;

        try{
            request = new DetectFacesRequest()
                    .withImage(new Image().withBytes(ByteBuffer.wrap(multipartFile.getBytes())))
                    .withAttributes(Attribute.ALL);
            detectFacesResult = amazonClient.detectFaces(request);
            return detectFacesResult.getFaceDetails();
        } catch (Exception e){
            return Collections.emptyList();
        }
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
            result = amazonClient.startLabelDetection(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return null;
        }

        return jobId;
    }

    @Override
    public GetLabelDetectionResult getLabelDetection(String jobId) {
        GetLabelDetectionRequest request = new GetLabelDetectionRequest()
                .withJobId(jobId)
                .withSortBy(LabelDetectionSortBy.TIMESTAMP);
        GetLabelDetectionResult result;
        try{
            result = amazonClient.getLabelDetection(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
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
            result = amazonClient.startCelebrityRecognition(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartCelebrityRecognition'";
        }
        return jobId;
    }

    @Override
    public GetCelebrityRecognitionResult getCelebrityRecognition(String jobId) {
        GetCelebrityRecognitionRequest request = new GetCelebrityRecognitionRequest()
                .withJobId(jobId)
                .withSortBy(CelebrityRecognitionSortBy.TIMESTAMP);
        GetCelebrityRecognitionResult result;

        try{
            result = amazonClient.getCelebrityRecognition(request);
        } catch (AmazonRekognitionException e){
            return null;
        }
        return result;
    }

    @Override
    public String startFaceDetection(String fileName, String bucketName) {
        S3Object s3Object = new S3Object().withBucket(bucketName).withName(fileName);
        Video video = new Video().withS3Object(s3Object);
        StartFaceDetectionRequest request = new StartFaceDetectionRequest()
                .withVideo(video);
        String jobId;
        try{
            StartFaceDetectionResult result = amazonClient.startFaceDetection(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartFaceDetection'";
        }

        return jobId;
    }

    @Override
    public GetFaceDetectionResult getFaceDetection(String jobId) {
        GetFaceDetectionRequest request = new GetFaceDetectionRequest()
                .withJobId(jobId);
        GetFaceDetectionResult result;
        try{
            result = amazonClient.getFaceDetection(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

    @Override
    public String startTextDetection(String fileName, String bucketName) {
        S3Object s3Object = new S3Object().withBucket(bucketName).withName(fileName);
        Video video = new Video().withS3Object(s3Object);
        StartTextDetectionRequest request = new StartTextDetectionRequest()
                .withVideo(video);
        String jobId;
        try{
            StartTextDetectionResult result = amazonClient.startTextDetection(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartTextDetection'";
        }

        return jobId;
    }

    @Override
    public GetTextDetectionResult getTextDetection(String jobId) {
        GetTextDetectionRequest request = new GetTextDetectionRequest()
                .withJobId(jobId);
        GetTextDetectionResult result;
        try{
            result = amazonClient.getTextDetection(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

    @Override
    public String startContentModeration(String fileName, String bucketName) {
        S3Object s3Object = new S3Object().withBucket(bucketName).withName(fileName);
        Video video = new Video().withS3Object(s3Object);
        StartContentModerationRequest request = new StartContentModerationRequest()
                .withVideo(video);
        String jobId;
        try{
            StartContentModerationResult result = amazonClient.startContentModeration(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartTextDetection'";
        }

        return jobId;
    }

    @Override
    public GetContentModerationResult getContentModeration(String jobId) {
        GetContentModerationRequest request = new GetContentModerationRequest()
                .withJobId(jobId)
                .withSortBy(ContentModerationSortBy.TIMESTAMP);
        GetContentModerationResult result;
        try{
            result = amazonClient.getContentModeration(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

    @Override
    public String startSegmentDetection(String fileName, String bucketName) {
        S3Object s3Object = new S3Object().withBucket(bucketName).withName(fileName);
        Video video = new Video().withS3Object(s3Object);
        StartSegmentDetectionRequest request = new StartSegmentDetectionRequest()
                .withVideo(video)
                .withSegmentTypes(SegmentType.SHOT);
        String jobId;
        try{
            StartSegmentDetectionResult result = amazonClient.startSegmentDetection(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartSegmentDetection'";
        }

        return jobId;
    }

    @Override
    public GetSegmentDetectionResult getSegmentDetection(String jobId) {
        GetSegmentDetectionRequest request = new GetSegmentDetectionRequest()
                .withJobId(jobId);
        GetSegmentDetectionResult result;
        try{
            result = amazonClient.getSegmentDetection(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

    @Override
    public String startPersonTracking(String fileName, String bucketName) {
        S3Object s3Object = new S3Object().withBucket(bucketName).withName(fileName);
        Video video = new Video().withS3Object(s3Object);
        StartPersonTrackingRequest request = new StartPersonTrackingRequest()
                .withVideo(video);
        String jobId;
        try{
            StartPersonTrackingResult result = amazonClient.startPersonTracking(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartPersonTracking";
        }
        return jobId;
    }

    @Override
    public GetPersonTrackingResult getPersonTracking(String jobId) {
        GetPersonTrackingRequest request = new GetPersonTrackingRequest()
                .withJobId(jobId)
                .withSortBy(PersonTrackingSortBy.TIMESTAMP);
        GetPersonTrackingResult result;
        try{
            result = amazonClient.getPersonTracking(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
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
            StartFaceSearchResult result = amazonClient.startFaceSearch(request);
            jobId = result.getJobId();
        } catch (AmazonRekognitionException e){
            return "Error 'StartFaceSearch";
        }
        return jobId;
    }

    @Override
    public GetFaceSearchResult getFaceSearch(String jobId) {
        GetFaceSearchRequest request = new GetFaceSearchRequest()
                .withJobId(jobId)
                .withSortBy(FaceSearchSortBy.TIMESTAMP);
        GetFaceSearchResult result;
        try{
            result = amazonClient.getFaceSearch(request);
        } catch (AmazonRekognitionException e){
            return null;
        }

        return result;
    }

    @Override
    public String uploadFile(MultipartFile file, String bucketName){
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        String path = fileObj.getPath();
        try {
            Files.delete(Path.of(path));
            return "File uploaded : " + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error 'PutObject'";
        }
    }


    public File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }
}
