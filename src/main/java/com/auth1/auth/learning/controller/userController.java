package com.auth1.auth.learning.controller;

import com.auth1.auth.learning.Models.Token;
import com.auth1.auth.learning.Models.user;
import com.auth1.auth.learning.dtos.loginRequestDto;
import com.auth1.auth.learning.dtos.signupRequestDto;
import com.auth1.auth.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/login")
    public Token login(@RequestBody loginRequestDto requestDto){

        return userService.login(requestDto.getEmail() ,
                requestDto.getPassword());

    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<Void> logout(@PathVariable("token") String token){

        userService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validateToken/{token}")
    public  boolean validateToken(@PathVariable("token") String token){

        return userService.validateToken(token);
    }

}
