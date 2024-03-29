package com.auth1.auth.learning.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
public class Token extends BaseModel{


    @OneToOne
    private user user;
    private Date expireAt;
    private String value;
    private boolean deleted;
}
