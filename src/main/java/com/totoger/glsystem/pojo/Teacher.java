package com.totoger.glsystem.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Teacher {
    private Long id;
    private String tname;
    private String username;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
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

    public void setPassword(String possword) {
        this.password = possword;
    }
}
