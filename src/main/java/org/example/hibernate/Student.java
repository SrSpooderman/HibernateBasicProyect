package org.example.hibernate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private int id;
    private String name;
    private String lastname;
    private String age;
    private String email;
    private String phone;

    // Builder
    public Student(String name, String lastname, String age, String email, String phone){
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public Student() {

    }
}
