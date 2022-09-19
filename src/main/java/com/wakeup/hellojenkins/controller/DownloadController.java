package com.wakeup.hellojenkins.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.wakeup.hellojenkins.listener.MyEvent;
import com.wakeup.hellojenkins.util.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RestController
public class DownloadController {

    @PostMapping("/download")
    public void downloadLocal(HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "test.txt".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("E:\\temp\\202207\\temp07.txt");// 文件的存放路径
//        InputStream inStream = new FileInputStream("E:\\temp\\202207\\汪方杰建筑设计.zip");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.addHeader("X-Accel-Limit-Rate", "1024");
//        RateLimiter limiter = RateLimiter.create(1024*200);
        System.out.println("开始 ==================>"+new Date());
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
//                limiter.acquire(len);
//            System.out.println("下载中==================>"+new Date());
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("***************下载完成***************"+new Date());
    }
}
