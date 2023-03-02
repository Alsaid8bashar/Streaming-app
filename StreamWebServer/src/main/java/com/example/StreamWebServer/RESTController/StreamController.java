package com.example.StreamWebServer.RESTController;

import com.example.StreamWebServer.DAO.videoDao;
import com.example.StreamWebServer.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.LazyContextVariable;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("Rest")
public class StreamController {
    private final RestTemplate restTemplate;
    private final videoDao dao;

    @Autowired
    public StreamController(RestTemplateBuilder builder, videoDao videoDao) {
        this.restTemplate = builder.build();
        this.dao = videoDao;
    }

    @GetMapping("/videos")
    public List<Video> getAllVideos() throws SQLException {
            return dao.getAll();
    }

    @GetMapping("/video")
    public Video getVideo(@RequestParam int id) throws SQLException {
        return dao.findById(id);
    }

}
