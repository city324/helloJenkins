package com.wakeup.hellojenkins.factory;

import com.wakeup.hellojenkins.pojo.Human;
import com.wakeup.hellojenkins.pojo.RedDog;
import com.wakeup.hellojenkins.pojo.User;
import com.wakeup.hellojenkins.util.SpringUtil;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Factory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DogFactory<Dog> implements FactoryBean<Dog> {

    //    public DogFactory(Class<?> clazz) {
//        if (clazz instanceof Dog) {
//            this.dog = (Dog) SpringUtil.getApplicationContext().getBean(clazz);
//        }
//    }

    public DogFactory() {
    }

    public DogFactory(Dog dog) {
        this.dog = dog;
    }

    private String type = null;
    private Dog dog = (Dog) new RedDog("aaa");

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Dog getObject() throws Exception {
        return dog;
    }

    @Override
    public Class<?> getObjectType() {
        return dog.getClass();
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
