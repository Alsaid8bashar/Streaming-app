package com.example.Authentication.Service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean checkCredential(String username, String password) {
        if (username.equals("bashar") && password.equals("1234")) return true;
        return false;
    }
}
