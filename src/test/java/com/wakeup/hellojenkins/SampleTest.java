package com.wakeup.hellojenkins;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.wakeup.hellojenkins.mapper.UserMapper;
import com.wakeup.hellojenkins.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        userMapper.insert(new User(null,"张三",18,"qeqw"));
//        userMapper.insert(new User(null,"张awda三",18,"qeqw"));
//        userMapper.insert(new User(null,"张da",18,"qeqw"));
//        userMapper.insert(new User(null,"张三daaed",18,"qeqw"));
//        List<User> userList = userMapper.selectList(new QueryWrapper<User>().lambda());
//        userList.forEach(System.out::println);
    }

}
