package com.odentist.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Role
 * @Date 2022/9/13 16:55
 * @Author jin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    private String role;
    private String permission;
}
