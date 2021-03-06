package com.wakeup.hellojenkins.listener;

import ch.qos.logback.core.util.Loader;
import com.wakeup.hellojenkins.inter.SpiInterface;
import com.wakeup.hellojenkins.inter.TestSpi;
import com.wakeup.hellojenkins.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
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

//@Component
public class MyAppDealListener implements ApplicationListener<ApplicationStartedEvent> {

/*
    @Autowired
    private SpiInterface spiInterface;
*/

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("ApplicationStartedEvent  ?????????");
/*        try {
            TestSpi.getSpiShwo();
//            spiInterface.show("????????????");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {

            Class cla = getClassFromJavaFile("E:/temp/com/lib/", "Third");
            ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) event.getApplicationContext();
            // ??????bean??????????????????DefaultListableBeanFactory
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
            // ??????BeanDefinitionBuilder??????bean??????
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(cla);
//        // ????????????userService,??????????????????????????????bean:userService,??????userService?????????spring???????????????.
//        beanDefinitionBuilder.addPropertyReference("user", "user");
            // ??????bean
            defaultListableBeanFactory.registerBeanDefinition(cla.getName(), beanDefinitionBuilder.getRawBeanDefinition());
            Object OBJ = defaultListableBeanFactory.getBean(cla);
            Method[] ms = cla.getMethods();
            for (Method m : ms) {
                if ("m1".equals(m.getName()) || "m2".equals(m.getName())) {
                    m.invoke(OBJ);
                    System.out.println(m);
                }
            }
        } catch (Exception e) {
            System.out.println("??????  GG==");
            e.printStackTrace();
        }

    }

    public static Class getClassFromJavaFile(String dirPath, String pakagePath) {
        if (StringUtils.isEmpty(dirPath) || StringUtils.isEmpty(pakagePath))//????????????????????????
            return null;

        String pakageDir = pakagePath.replaceAll("\\.", "/");// ??????????????? . ????????? / , ????????????pakageDir = com/chenyf/entity
        String filePath = dirPath.concat(pakageDir).concat(".java");// src/main/java???java?????????????????????

        //??????
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        int compilationResult = javac.run(null, null, null, filePath);

        if (compilationResult != 0)//compilationResult == 0,????????????????????????Java???????????????????????????????????????class??????
            throw new IllegalArgumentException("????????????");

        Class<?> clazz = null;
        try {

            MyClassLoader loader = new MyClassLoader(dirPath);//???????????????ClassLoader
            clazz = loader.findClass(pakagePath);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return clazz;
    }

    private static final class MyClassLoader extends ClassLoader {

        private String classDir;// ?????????????????????:"file:/today/javadir/src/main/java/"

        @Override
        public Class<?> findClass(String name) {
            String realPath = classDir + name.replace(".", "/") + ".class";//class?????????????????????
            byte[] cLassBytes = null;
            Path path = null;

            try {

                path = Paths.get(new URI(realPath));
                cLassBytes = Files.readAllBytes(path);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);//???????????????defineClass??????
            return clazz;
        }

        public MyClassLoader(String classDir) {
            this.classDir = "file:/".concat(classDir);//?????? ???file:/?????????
        }

    }
}
