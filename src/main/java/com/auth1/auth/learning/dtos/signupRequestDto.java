package com.auth1.auth.learning.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class signupRequestDto {
    private String name;
    private String email;
    private String password;
}
