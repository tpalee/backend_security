package com.example.demo_security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="authorities")
public class Authority implements Serializable {


@Id
    @Column(nullable = false)
    private String username;


@Id
    @Column(nullable = false)
    private String authority;

//constructor


    public Authority(String username, String authority){
    this.username=username;
    this.authority=authority;

    }

    public Authority(){}





    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
