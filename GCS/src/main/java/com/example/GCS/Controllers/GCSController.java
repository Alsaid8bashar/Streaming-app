package com.example.GCS.Controllers;

import com.example.GCS.service.GCSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;

@RestController
@RequestMapping("GCS")
public class GCSController {

    private final GCSService gcsService;

    @Autowired
    public GCSController(GCSService gcsService) {
        this.gcsService = gcsService;
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile videoFile,@RequestParam String videoName)throws IOException {
        return gcsService.uploadFile(videoFile,videoName);
    }

    @GetMapping("/stream")
    public StreamingResponseBody streamVideo(@RequestParam String videoName) throws IOException {
        return gcsService.getVideo(videoName);
    }
}

