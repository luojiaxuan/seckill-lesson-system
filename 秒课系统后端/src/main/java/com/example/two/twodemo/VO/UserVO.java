package com.example.two.twodemo.VO;
import java.io.Serializable;
public class UserVO implements Serializable{

    private static final long serialVersionUID = 3178076107863003651L;
    private String username;

    private String password;

    private long id;

    private String salt;


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


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getSalt() {
        return salt;
    }


    public void setSalt(String salt) {
        this.salt = salt;
    }

}
