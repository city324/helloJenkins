package com.wakeup.hellojenkins.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wakeup.hellojenkins.listener.MyEvent;
import com.wakeup.hellojenkins.mapper.UserMapper;
import com.wakeup.hellojenkins.pojo.User;
import com.wakeup.hellojenkins.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/create")
//    @Transactional
    public String helloDocker(@RequestParam int size) {
        try {
            long start = System.currentTimeMillis();
            ExecutorService es = Executors.newFixedThreadPool(8);
            Random random = new Random(120);
            String[] nameWords = "啊发撒打发阿斯弗个人的活动各色活动是否合适的是外国商人色纷纷施活动是否合适的是外国商人色纷纷施工合同就很有建设性电话停机啊发撒打发阿斯弗个人的活动各色活动是否合适的是外国商人色纷纷施活动是否合适的是外国商人色纷纷施工合同就很有建设性电话停机".split("");
            // 10万个ueser的创建
            CountDownLatch latch = new CountDownLatch(size);
            System.out.println("====================================================   Start=============>");
            for (int i = 0; i < size; i++) {
                Future<?> future = es.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            User user = new User();
                            user.setAge(random.nextInt(120));
                            user.setName(nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)]);
                            user.setEmail(nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)]);
                            System.out.println(user.toString());
                            userMapper.insert(user);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw e;
                        }
                        latch.countDown();//每个任务执行完就countDown一次
                    }
                });
                Object o = future.get();
            }
            latch.await();//阻塞，直到计数器的值为0，才让主线程往下执行
            es.shutdown();//关闭线程池
            long end = System.currentTimeMillis();
            System.out.println("====================================================  over =============>");
            return end - start + "毫秒";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    @GetMapping("/insertOne")
//    @Transactional
    public String insertOne() {
        try {
            long start = System.currentTimeMillis();
            Random random = new Random(120);
            String[] nameWords = "啊发撒打发阿斯弗个人的活动各色活动是否合适的是外国商人色纷纷施活动是否合适的是外国商人色纷纷施工合同就很有建设性电话停机啊发撒打发阿斯弗个人的活动各色活动是否合适的是外国商人色纷纷施活动是否合适的是外国商人色纷纷施工合同就很有建设性电话停机".split("");
            // 10万个ueser的创建
            System.out.println("====================================================   Start=============>");
            User user = new User();
            user.setAge(random.nextInt(120));
            user.setName(nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)]);
            user.setEmail(nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)] + nameWords[random.nextInt(100)]);
            System.out.println(user.toString());
            userMapper.insert(user);
            long end = System.currentTimeMillis();
            System.out.println("====================================================  over =============>");
            return end - start + "毫秒";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    @GetMapping("/select")
//    @Transactional
    public String select() {
        Random random = new Random();
        User user = userMapper.selectById(random.nextInt(119017));
        System.out.println("====================================================  over =============>");
        return user.toString();
    }

    @GetMapping("/checkWapper")
//    @Transactional
    public String checkWapper() {
        QueryWrapper<User> qw1 = new QueryWrapper<>();
        qw1.eq("name", "zs");
        qw1.in("age", new ArrayList<>());
        List<User> users = userMapper.selectList(qw1);
        for (User user : users) {
            System.out.println("=======>" + user.toString());
        }
        return "";
    }


    @GetMapping("/addUser")
//    @Transactional
    public String addUser() {
        User user = new User();
        user.setAge(1);
        user.setName("name");
        user.setEmail("email");
        System.out.println(user.toString());
        userMapper.addUser(user);
        return "失败";
    }
}
