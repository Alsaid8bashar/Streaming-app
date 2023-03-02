package com.example.Authentication.Controllers;

import com.example.Authentication.Service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Auth")
public class AuthenticationController {
    final LoginService loginService;

    public AuthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/checkCredentials")
    public boolean checkCredential(@RequestParam String username, @RequestParam String password) {
    return loginService.checkCredential(username,password);
    }
}
