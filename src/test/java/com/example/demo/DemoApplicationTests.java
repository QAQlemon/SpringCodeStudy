package com.example.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.swing.*;
import java.util.function.BiFunction;

interface Base{
    default public int func(int a){
        return a;
    }
}
class B implements Base{

    @Override
    public int func(int a ) {
        return a;
    }
}
class A{
    public void  func(BiFunction<Base, Integer, Integer> action, int arg){
        B b = new B();
        Object res = action.apply(b,arg);
    }
}


//@SpringBootTest
class DemoApplicationTests {
    public static void main(String[] args) {
        A a = new A();
//        BiFunction<Base,Integer,Integer> f = Base::func;

        a.func(Base::func,1);
    }

    @Test
    void contextLoads() {
        //从spring.factories文件中注册拓展接口：
        //  ApplicationContextInitializer
        //  ApplicationListener
        SpringApplication springApplication = new SpringApplication(MyConfiguration.class);
        springApplication.setApplicationStartup(new FlightRecorderApplicationStartup());

        //创建context
        //refresh前ApplicationContextInitializer.initialize()
        springApplication.run();

//        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder();
//        springApplicationBuilder.applicationStartup();

//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(MyConfiguration.class);
//        context.refresh();
//        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
//        BeanDemo bean = context.getBean(BeanDemo.class);
//        System.out.println(bean);
    }
}

@Component
//@Configuration(proxyBeanMethods = false)
//@Configuration(proxyBeanMethods = true)
class MyConfiguration{
    @Bean(
        initMethod = "init",
        destroyMethod = "destroy"
    )
    public BeanDemo beanDemo(){
        return new BeanDemo();
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