package com.example.Front.End.Controller;


import com.example.Front.End.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
@RequestMapping("stream")
public class StreamController {

    private final RestTemplate restTemplate;
    private final Environment environment;
    @Autowired
    public StreamController(RestTemplateBuilder builder, Environment env) {
        this.restTemplate = builder.build();
        this.environment = env;
    }

    @GetMapping("/video")
    public String video(@RequestParam int videoId, Model model) {
        Video result = restTemplate.getForObject(environment.getProperty("Streaming_service")+"/Rest/video?id=" + videoId, Video.class);
        model.addAttribute("video", result);
        return "VideoPage";
    }

    @GetMapping("/videos")
    public String videos(Model model) {
        List<Video> result = restTemplate.exchange(
                environment.getProperty("Streaming_service")+"/Rest/videos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Video>>() {
                }
        ).getBody();
        model.addAttribute("videos", result);
        return "Videos";
    }
}
