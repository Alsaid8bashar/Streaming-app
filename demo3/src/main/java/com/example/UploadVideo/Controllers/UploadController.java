package com.example.UploadVideo.Controllers;


import com.example.UploadVideo.Service.VideoService;
import com.example.UploadVideo.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

@RestController
public class UploadController {

    final VideoService videoService;


    @Autowired
    public UploadController(VideoService videoService) {
        this.videoService = videoService;
    }


    @PostMapping("/upload")
    public ResponseEntity<Video> uploadVideo(@RequestPart("video") Video video, @RequestPart("videoFile") MultipartFile videoFile) throws SQLException {
        videoService.uploadVideo(video, videoFile);
        return ResponseEntity.ok(video);
    }

}
