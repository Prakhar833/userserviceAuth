package com.auth1.auth.learning.controller;

import com.auth1.auth.learning.Models.user;
import com.auth1.auth.learning.dtos.signupRequestDto;
import com.auth1.auth.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public user signup(@RequestBody signupRequestDto requestDto){

        return userService.signup(requestDto.getName(),requestDto.getPassword(),
                requestDto.getEmail());
        
    }

}
