package com.example.UploadVideo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {
    private final RestTemplate restTemplate;


    @Autowired
    public LoginService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public boolean checkCredential( String username,  String password) {
        boolean result2 = false;
        Boolean result = restTemplate.postForObject("http://localhost:8080/Auth/checkCredentials?username="
                + username + "&password=" + password, result2, Boolean.class);
        return result;
    }
}
