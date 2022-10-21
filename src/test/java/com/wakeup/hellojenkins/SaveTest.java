package com.wakeup.hellojenkins;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.wakeup.hellojenkins.mapper.UserMapper;
import com.wakeup.hellojenkins.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SaveTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> users = new ArrayList<>();
        users.add(new User(null, "张三", 18, "qeqw"));
        users.add(new User(null, "张awda三", 18, "qeqw"));
        users.add(new User(null, "张da", 18, "qeqw"));
        users.add(new User(null, "张三daaed", 18, "qeqw"));
        userMapper.insertBatchSomeColumn(users);
    }

    @Test
    public void testAdd() {
        System.out.println(("----- selectAll method test ------"));
        User user = new User(null, "qwerrrrr", 18, "qwerrrrr");
        DynamicDataSourceContextHolder.push("pgsql");
        userMapper.addUser(user);

    }
}
