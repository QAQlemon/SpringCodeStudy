package com.example.demo.config;

import com.example.demo.hook.BeanDemo;
import com.example.demo.hook.MyApplicationContextInitializer;
import com.example.demo.hook.SpringLifeHook;
import jakarta.annotation.Priority;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyConfiguration
 * @Description:
 * @Author: zc
 * @Create: 2025/7/7 15:33
 * @Version: v1.0
 */
@ToString
@Getter
@Setter
//@Component
@Configuration//注：配置类不能
@PropertySource("classpath:customProperties/BeanDemoCfg.properties")//仅导入.properties文件,指定属性文件的加载来源，加载文件到Environment中
//@ConfigurationProperties(prefix = "bean")//不能使用相同的前缀，
//@ImportResource("classpath:BeanDemoCfg.yml")//导入.xml文件
public class HookConfiguration {
    @Value("${server.port}")
    Integer port;

    @Value("${profile}")
    String profile;

    @Value("${beanData.name}")
    String name;

    @Value("${bean-data.age}")
    Integer age;


    @Bean(
            initMethod = "init",
            destroyMethod = "destroy"
    )
//    @ConfigurationProperties(prefix = "myBean")
    public BeanDemo beanDemo(){
        BeanDemo beanDemo = new BeanDemo();
        return beanDemo;
    }
    ///////////////////////////////////////////////////////////////////
    //ApplicationContextInitializer
    @Bean
    public MyApplicationContextInitializer myApplicationContextInitializer(){
        MyApplicationContextInitializer myApplicationContextInitializer = new MyApplicationContextInitializer();
        return myApplicationContextInitializer;
    }

    ///////////////////////////////////////////////////////////////////
    //BeanFactoryPostProcessor
    //必须使用static
    @Bean
    public static SpringLifeHook.MyBeanFactoryPostProcessor myBeanFactoryPostProcessor(){
        SpringLifeHook.MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new SpringLifeHook.MyBeanFactoryPostProcessor();
        return myBeanFactoryPostProcessor;
    }
    //注：@Configuration 类默认会被 Spring CGLIB 代理增强,@Configuration 中所有带 @Bean(proxyBeanMethods=true) 注解的方法都会被动态代理
    //而BeanDefinitionRegistryPostProcessor执行时，常规的 @Configuration 类增强机制还未完全准备好，需要使用static标识方法
    @Bean("MyBeanDefinitionRegistryPostProcessor1")
    //此处@Order和@Priority不生效：BeanFactoryPostProcessor的顺序需要从Class对象上获取 Ordered/PriorityOrdered接口 或 @Order/@priority注解
//    @Priority(1)
//    @Order(1)
    public static SpringLifeHook.MyBeanDefinitionRegistryPostProcessor myBeanDefinitionRegistryPostProcessor1(){
        SpringLifeHook.MyBeanDefinitionRegistryPostProcessor myBeanDefinitionRegistryPostProcessor = new SpringLifeHook.MyBeanDefinitionRegistryPostProcessor();
        return myBeanDefinitionRegistryPostProcessor;
    }

    ///////////////////////////////////////////////////////////////////
    //BeanPostProcessor
    @Bean
    public SpringLifeHook.MyBeanPostProcessor myBeanPostProcessor(){
        SpringLifeHook.MyBeanPostProcessor myBeanPostProcessor = new SpringLifeHook.MyBeanPostProcessor();
        return myBeanPostProcessor;
    }

    @Bean
    SpringLifeHook.MyInstantiationAwareBeanPostProcessor myInstantiationAwareBeanPostProcessor(){
        SpringLifeHook.MyInstantiationAwareBeanPostProcessor myInstantiationAwareBeanPostProcessor = new SpringLifeHook.MyInstantiationAwareBeanPostProcessor();
        return myInstantiationAwareBeanPostProcessor;
    }

    @Bean
    SpringLifeHook.MyMergedBeanDefinitionPostProcessor myMergedBeanDefinitionPostProcessor(){
        SpringLifeHook.MyMergedBeanDefinitionPostProcessor myMergedBeanDefinitionPostProcessor = new SpringLifeHook.MyMergedBeanDefinitionPostProcessor();
        return myMergedBeanDefinitionPostProcessor;
    }
}