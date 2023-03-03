package com.example.Front.End.Controller;

import com.example.Front.End.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

@Controller
@RequestMapping("upload")
public class UploadVideoController {
    private final RestTemplate restTemplate;
    private final Environment environment;

    @Autowired
    public UploadVideoController(RestTemplateBuilder builder, Environment environment) {
        this.restTemplate = builder.build();
        this.environment = environment;
    }

    @GetMapping("/")
    public String uploadPage() {
        return "uploadPage";
    }

    @PostMapping("/uploadVideo")
    public String uploadVideo(@ModelAttribute Video video, @RequestParam("videoFile") MultipartFile videoFile) throws SQLException {
        String apiUrl = environment.getProperty("Upload_service")+"/upload";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("video", video);
        body.add("videoFile", videoFile.getResource());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.postForObject(apiUrl, request, Video.class);
        return "redirect:/upload/";
    }


}
