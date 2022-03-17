//package com.wakeup.hellojenkins.listener;
//
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.tools.JavaCompiler;
//import javax.tools.ToolProvider;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class MyClassLoader extends ClassLoader {
//    private String classDir;// 文件目录，例如:"file:/today/javadir/src/main/java/"
//
//    public MyClassLoader(String classDir) {
//        this.classDir = "file:/".concat(classDir);//拼接 “file:/”前缀
//    }
//
//    public static Class getClassFromJavaFile(String dirPath, String pakagePath) {
//        if (StringUtils.isEmpty(dirPath) || StringUtils.isEmpty(pakagePath))//校验参数是否为空
//            return null;
//
//        String pakageDir = pakagePath.replaceAll("\\.", "/");// 将路径中的 . 替换为 / , 替换后的pakageDir = com/chenyf/entity
//        String filePath = dirPath.concat(pakageDir).concat(".java");// src/main/java为java文件的特定目录
//
//        //编译
//        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
//        int compilationResult = javac.run(null, null, null, filePath);
//
//        if (compilationResult != 0)//compilationResult == 0,说明编译成功，在Java文件的同目录下会生成相应的class文件
//            throw new IllegalArgumentException("编译失败");
//
//        Class<?> clazz = null;
//        try {
//            MyClassLoader loader = new MyClassLoader(dirPath);//使用自定义ClassLoader
//            clazz = loader.findClass(pakagePath);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return clazz;
//    }
//
//    @Override
//    protected Class<?> findClass(String name) {
//        Class clazz = null;
//        String myPath = "file:/".concat(name);
//        ;
//        System.out.println(myPath);
//        byte[] cLassBytes = null;
//        Path path = null;
//        try {
//            path = Paths.get(new URI(myPath));
//            cLassBytes = Files.readAllBytes(path);
//            clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return clazz;
//    }
//}