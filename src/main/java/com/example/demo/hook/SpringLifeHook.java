package com.example.demo.hook;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.annotation.Order;

/**
 * @ClassName: SpringLifeHook
 * @Description:
 * @Author: zc
 * @Create: 2025/7/4 13:36
 * @Version: v1.0
 */
public class SpringLifeHook {

    //1.BeanFactoryPostProcessor
    @Order(100)//BeanFactoryPostProcessor排序原则: Priority接口->Ordered接口->@Order注解->@Priority (这里不清楚为什么@Order和@Priority反着处理)
    public static class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
            System.out.println("==>BeanDefinitionRegistryPostProcessor接口：postProcessBeanDefinitionRegistry()");
        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            System.out.println("==>BeanDefinitionRegistryPostProcessor接口：postProcessBeanFactory()");
        }
    }

    public static class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            System.out.println("==>BeanFactoryPostProcessor接口：postProcessBeanFactory()");
        }
    }

    //2.BeanPostProcessor
    public static class MyBeanPostProcessor implements BeanPostProcessor{

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if(beanName.equals("beanDemo"))
            {
                System.out.println("--BeanPostProcessor接口：postProcessBeforeInstantiation,"+beanName);
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if(beanName.equals("beanDemo"))
            {
                System.out.println("--BeanPostProcessor接口：postProcessAfterInitialization,"+beanName);
            }
            return bean;
        }
    }

    //实例化
    public static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor{
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(beanName.equals("beanDemo")) {
                System.out.println("InstantiationAwareBeanPostProcessor接口：postProcessBeforeInstantiation");
            }
            return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if(beanName.equals("beanDemo")) {
                System.out.println("InstantiationAwareBeanPostProcessor接口：postProcessAfterInstantiation");
            }
            return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if(beanName.equals("beanDemo")) {
                System.out.println("InstantiationAwareBeanPostProcessor接口：postProcessProperties()");
            }
            return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
        }
    }
    public static class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    }
    // bean 实例化前访问和修改合并后的 BeanDefinition,为自动装配(@Autowired, @Value等)收集必要的元数据
    public static class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

        @Override
        public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
            if(beanName.equals("beanDemo"))
            {
                System.out.println("MyMergedBeanDefinitionPostProcessor接口：postProcessMergedBeanDefinition");
            }
        }

        @Override
        public void resetBeanDefinition(String beanName) {
            if(beanName.equals("beanDemo"))
            {
                System.out.println("MyMergedBeanDefinitionPostProcessor接口：resetBeanDefinition");
            }
        }

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if(beanName.equals("beanDemo"))
            {
                System.out.println("MyMergedBeanDefinitionPostProcessor接口：postProcessBeforeInitialization");
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if(beanName.equals("beanDemo"))
            {
                System.out.println("MyMergedBeanDefinitionPostProcessor接口：postProcessAfterInitialization");
            }
            return bean;
        }
    }

}
