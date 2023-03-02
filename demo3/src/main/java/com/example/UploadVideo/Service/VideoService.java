package com.example.UploadVideo.Service;


import com.example.UploadVideo.DAO.videoDao;
import com.example.UploadVideo.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Service
public class VideoService {

    private final RestTemplate restTemplate;
    private final videoDao dao;
    private final Environment environment;

    @Autowired
    public VideoService(RestTemplateBuilder builder, videoDao videoDao, Environment environment) {
        this.restTemplate = builder.build();
        this.dao = videoDao;
        this.environment = environment;
    }

    public ResponseEntity<String> uploadVideo(Video video, MultipartFile videoFile) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        Date date = new Date(System.currentTimeMillis());
        String timestamp = formatter.format(date);
        String videoName = videoFile.getResource().getFilename() + timestamp;
        video.setVideoPath(environment.getProperty("videos_path") + videoName);
        dao.add(video);
        String apiUrl = environment.getProperty("google_cloud_storage_service") + "/upload";
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("videoFile", videoFile.getResource());
        body.add("videoName", videoName);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
            System.out.println(response.getStatusCode());
        } catch (HttpClientErrorException e) {
            System.out.println("Error uploading video: " + e.getMessage());
        }
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }
}
