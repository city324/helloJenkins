package com.wakeup.hellojenkins;

import com.wakeup.hellojenkins.factory.DogFactory;
import com.wakeup.hellojenkins.pojo.Dog;
import com.wakeup.hellojenkins.pojo.Human;
import com.wakeup.hellojenkins.pojo.RedDog;
import com.wakeup.hellojenkins.util.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
       /* Human human1 = (Human) SpringUtil.getApplicationContext().getBean("human");
        Human human2 = (Human) SpringUtil.getApplicationContext().getBean("human");
        System.out.println(human1.user.getName());
        System.out.println(human1.user);
        System.out.println(human2.user.getName());
        System.out.println(human2.user);
        System.out.println(human1.user.getEmail());*/
 /*       Human human3 = new Human();
        System.out.println(human3.user);
        System.out.println(human3.user.getEmail());
        Human human4 = new Human();
        System.out.println(human4.user);
        System.out.println(human4.user.getEmail());*/
//        Dog dog1 = (Dog) SpringUtil.getApplicationContext().getBean("dogFactory");
//        System.out.println(dog1);
//        System.out.println(dog1.getName());


//        Dog dog2 = (Dog) SpringUtil.getApplicationContext().getBean("dogFactory", new RedDog("rrrr"));
//        DogFactory dogFactory = (DogFactory) SpringUtil.getApplicationContext().getBean("&dogFactory", new RedDog("rrrr"));
//        System.out.println(dog2);
//        System.out.println(dogFactory.getObjectType());
    }

}
