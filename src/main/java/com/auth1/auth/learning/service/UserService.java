package com.auth1.auth.learning.service;

import com.auth1.auth.learning.Models.Token;
import com.auth1.auth.learning.Models.user;
import com.auth1.auth.learning.Repository.TokenRepository;
import com.auth1.auth.learning.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public user signup(String name, String password , String email){
        user user = new user();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    public Token login(String email, String password) {
        Optional<user> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            throw new RuntimeException("Invalid use and password");
        }

        user user = userOptional.get();

        if(!bCryptPasswordEncoder.matches(password , user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        Token token = new Token();
        token.setUser(user);
        token.setValue(UUID.randomUUID().toString());

        Date expireDate = getExpireDate();

        token.setExpireAt(expireDate);
        return tokenRepository.save(token);

    }

    private Date getExpireDate() {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(new Date());

        calendarDate.add(Calendar.DAY_OF_MONTH,30);
        Date expireTime = calendarDate.getTime();
        return expireTime;
    }

    public void logout(String token) {

        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeletedEquals(token , false);

        if(tokenOptional.isEmpty()){
            throw new RuntimeException("Token is Invalid");
        }
        Token tokenObject = tokenOptional.get();
        tokenObject.setDeleted(true);

        tokenRepository.save(tokenObject);
    }

    public boolean validateToken(String token) {

         /*
          To validate token
          1. Check if token value is present
          2. Check if token is not deleted
          3. Check if token is not expired
         */


        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeletedEqualsAndExpireAtGreaterThan(
                token , false , new Date());

        if(tokenOptional.isEmpty()){
            return false;
        }
        return true;
    }
}
