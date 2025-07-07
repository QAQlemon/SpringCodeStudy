package com.example.demo.hook;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName: BeanDemo
 * @Description:
 * @Author: zc
 * @Create: 2025/7/4 13:35
 * @Version: v1.0
 */
@ToString
//@Component
public class BeanDemo
        implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
//BeanPostProcessor,
//InstantiationAwareBeanPostProcessor,
        InitializingBean {
    String name = "zc";
    Integer age = 1;


    //1.Aware接口
    //BeanNameAware
    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware接口:" + name);
    }

    //BeanFactoryAware
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware接口");
    }

    //ApplicationContextAware
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware接口");
    }

    //2.
    //注：java注解
    @PostConstruct//在构造函数执行完毕后自动调用
    public void postConstruct() {
        System.out.println("@PostConstruct");
    }


    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy");
    }

    public void init() {
        System.out.println("@Bean的init()");
    }

    public void destroy() {
        System.out.println("@Bean的destroy()");
    }

    //2.BeanPostProcessor
    //在 Bean 初始化前后进行自定义操作
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("BeanPostProcessor接口：postProcessBeforeInitialization()");
//        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
//    }
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("BeanPostProcessor接口：postProcessAfterInitialization()");
//        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
//    }
//    @Override
//    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
//        System.out.println("BeanPostProcessor接口：postProcessProperties()");
//        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
//    }
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("BeanPostProcessor接口：postProcessBeforeInitialization()");
//        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("BeanPostProcessor接口：postProcessAfterInitialization()");
//        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
//    }


    //InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean接口：afterPropertiesSet()");
    }


}
