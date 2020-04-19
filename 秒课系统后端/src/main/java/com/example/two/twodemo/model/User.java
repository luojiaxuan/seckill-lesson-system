package com.example.two.twodemo.model;

import org.hibernate.annotations.Proxy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Proxy(lazy=false)
@Table(name="user")
public class User implements Serializable{
    private static final long serialVersionUID = 7521391360002308184L;
    public User(){}
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    @Id
    @Column(name="username", nullable = false)
    @NotBlank(message="用户名不能为空")
    private String username;

    @NotBlank(message="密码不能为空")
    @Column(name="password", nullable = false)
    @Size(min=4,message = "密码应该长度介于4到20之间")
    private String password;

    @Column(name="id")
    private long id;

    @Column(name= "salt", nullable = false)
    private String salt;


    //private String repassword;


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", salt='" + salt + '\'' +
                '}';
    }
}
