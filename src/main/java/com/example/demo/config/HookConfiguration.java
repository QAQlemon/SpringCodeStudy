package com.example.demo.config;

import com.example.demo.hook.BeanDemo;
import com.example.demo.hook.MyApplicationContextInitializer;
import com.example.demo.hook.SpringLifeHook;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyConfiguration
 * @Description:
 * @Author: zc
 * @Create: 2025/7/7 15:33
 * @Version: v1.0
 */

//@Component
@Configuration
@PropertySource("classpath:BeanDemoCfg.properties")//仅导入.properties文件,指定属性文件的加载来源，加载文件到Environment中
//@PropertySource("classpath:application.yml")//
//@ConfigurationProperties(prefix = "beanDemo")//将属性绑定到Java对象,无需借助@value
//@ImportResource("classpath:BeanDemoCfg.yml")//导入.xml文件
@ToString
@Getter
@Setter
public class HookConfiguration {
    @Value("${beanDemo.age}")
    Integer name;


    @Bean(
            initMethod = "init",
            destroyMethod = "destroy"
    )
//    @ConfigurationProperties(prefix = "myBean")
    public BeanDemo beanDemo(){
        BeanDemo beanDemo = new BeanDemo();
//        beanDemo.name=;
//        beanDemo.age=;
        return beanDemo;
    }
    @Bean
    public MyApplicationContextInitializer myApplicationContextInitializer(){
        MyApplicationContextInitializer myApplicationContextInitializer = new MyApplicationContextInitializer();
        return myApplicationContextInitializer;
    }
    @Bean
    public SpringLifeHook.MyBeanFactoryPostProcessor myBeanFactoryPostProcessor(){
        SpringLifeHook.MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new SpringLifeHook.MyBeanFactoryPostProcessor();
        return myBeanFactoryPostProcessor;
    }
    //注：@Configuration 类默认会被 Spring CGLIB 代理增强,@Configuration 中所有带 @Bean(proxyBeanMethods=true) 注解的方法都会被动态代理
    //而BeanDefinitionRegistryPostProcessor执行时，常规的 @Configuration 类增强机制还未完全准备好，需要使用static标识方法
    @Bean
    public SpringLifeHook.MyBeanDefinitionRegistryPostProcessor myBeanDefinitionRegistryPostProcessor(){
        SpringLifeHook.MyBeanDefinitionRegistryPostProcessor myBeanDefinitionRegistryPostProcessor = new SpringLifeHook.MyBeanDefinitionRegistryPostProcessor();
        return myBeanDefinitionRegistryPostProcessor;
    }
//    @Bean SpringLifeHook.MyInstantiationAwareBeanPostProcessor myInstantiationAwareBeanPostProcessor(){
//        SpringLifeHook.MyInstantiationAwareBeanPostProcessor myInstantiationAwareBeanPostProcessor = new SpringLifeHook.MyInstantiationAwareBeanPostProcessor();
//        return myInstantiationAwareBeanPostProcessor;
//    }
}