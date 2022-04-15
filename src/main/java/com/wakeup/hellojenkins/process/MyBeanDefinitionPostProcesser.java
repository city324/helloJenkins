package com.wakeup.hellojenkins.process;

import com.wakeup.hellojenkins.listener.MyAppDealListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;


public class MyBeanDefinitionPostProcesser implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
// 注册Bean定义，容器根据定义返回bean
        Class cla = MyAppDealListener.getClassFromJavaFile("E:/temp/", "com.Third");
        //构造bean定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(cla);
        //设置依赖
//        beanDefinitionBuilder.addPropertyReference("personDao", "personDao");
        BeanDefinition personManagerBeanDefinition = beanDefinitionBuilder
                .getRawBeanDefinition();
        //注册bean定义
        beanDefinitionRegistry.registerBeanDefinition("third", personManagerBeanDefinition);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
