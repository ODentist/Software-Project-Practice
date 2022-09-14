package com.odentist.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//员工表
@Data
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private String departmentName;
    private Date birth;
    private String password;
    private String role;

    public Employee(Integer id, String lastName, String email, String gender, String department, Date birth) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.departmentName = department;
        this.birth = birth;
    }
}
