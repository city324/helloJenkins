package com.wakeup.hellojenkins.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Value("ZS")
    private String name;
    private Integer age;
    @Value("11111")
    private String email;

    public User(Integer id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
