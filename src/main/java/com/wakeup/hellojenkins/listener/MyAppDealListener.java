package com.wakeup.hellojenkins.listener;

import ch.qos.logback.core.util.Loader;
import com.wakeup.hellojenkins.inter.TestSpi;
import com.wakeup.hellojenkins.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MyAppDealListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("ApplicationStartedEvent  发生了");
        try {
            TestSpi.getSpi();
        } catch (Exception e) {
            e.printStackTrace();
        }
   /*     try {

            Class cla = getClassFromJavaFile("E:/temp/", "com.Third");
            Method[] ms = cla.getMethods();
            for (Method m : ms) {
                    System.out.println(m);
            }
        } catch (Exception e) {
            System.out.println("报错  GG==");
            e.printStackTrace();
        }*/
    }

    public static Class getClassFromJavaFile(String dirPath, String pakagePath) {
        if (StringUtils.isEmpty(dirPath) || StringUtils.isEmpty(pakagePath))//校验参数是否为空
            return null;

        String pakageDir = pakagePath.replaceAll("\\.", "/");// 将路径中的 . 替换为 / , 替换后的pakageDir = com/chenyf/entity
        String filePath = dirPath.concat(pakageDir).concat(".java");// src/main/java为java文件的特定目录

        //编译
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        int compilationResult = javac.run(null, null, null, filePath);

        if (compilationResult != 0)//compilationResult == 0,说明编译成功，在Java文件的同目录下会生成相应的class文件
            throw new IllegalArgumentException("编译失败");

        Class<?> clazz = null;
        try {

            MyClassLoader loader = new MyClassLoader(dirPath);//使用自定义ClassLoader
            clazz = loader.findClass(pakagePath);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return clazz;
    }

    private static final class MyClassLoader extends ClassLoader {

        private String classDir;// 文件目录，例如:"file:/today/javadir/src/main/java/"

        @Override
        public Class<?> findClass(String name) {
            String realPath = classDir + name.replace(".", "/") + ".class";//class文件的真实路径
            byte[] cLassBytes = null;
            Path path = null;

            try {

                path = Paths.get(new URI(realPath));
                cLassBytes = Files.readAllBytes(path);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);//调用父类的defineClass方法
            return clazz;
        }

        public MyClassLoader(String classDir) {
            this.classDir = "file:/".concat(classDir);//拼接 “file:/”前缀
        }

    }
}
