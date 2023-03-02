package com.example.Front.End.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {
    private final Environment environment;
    private final RestTemplate restTemplate;

    @Autowired
    public LoginController(RestTemplateBuilder builder, Environment environment) {
        this.restTemplate = builder.build();
        this.environment = environment;
    }

    @GetMapping("/")
    public String login() {
        return "LoginPage";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean result2 = false;
        Boolean result = restTemplate.postForObject(environment.getProperty("Authentication_service")+"/checkCredentials?username=" + username + "&password=" + password, result2, Boolean.class);
        if (result) {
            return "redirect:/stream/videos";
        } else {
            return "redirect:/";
        }
    }
}
