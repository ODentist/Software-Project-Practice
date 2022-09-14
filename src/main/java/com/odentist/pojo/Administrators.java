package com.odentist.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Administrators
 * @Date 2022/9/13 11:14
 * @Author jin
 */
@Data
@NoArgsConstructor
public class Administrators {

    private int id;

    private String username;

    private String password;

    private String role;

    public Administrators(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
