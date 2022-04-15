package com.wakeup.hellojenkins;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("com.wakeup.hellojenkins.mapper")
public class HelloJenkinsApplication {
//    @Autowired
//    private ApplicationContext applicationContext;
    //            ApplicationContext applicationContext = SpringUtil.getApplicationContext();
//            DefaultListableBeanFactory factory = (DefaultListableBeanFactory) applicationContext.getParentBeanFactory();
//            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(cla);
//            factory.registerBeanDefinition(cla.getName(), beanDefinitionBuilder.getRawBeanDefinition());
//            factory.getBean(cla);
    public static void main(String[] args) {
        SpringApplication.run(HelloJenkinsApplication.class, args);
    }

}
