package com.auth1.auth.learning.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class user extends BaseModel{

    private String name;
    private String email;
    private String password;

    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerifies;

    public user() {
    }

    public user(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isEmailVerifies = false;
        this.roles = new ArrayList<>();
    }
}
