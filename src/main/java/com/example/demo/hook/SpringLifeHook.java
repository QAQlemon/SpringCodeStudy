package com.example.demo.hook;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @ClassName: SpringLifeHook
 * @Description:
 * @Author: zc
 * @Create: 2025/7/4 13:36
 * @Version: v1.0
 */
public class SpringLifeHook {

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
//    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor{
////        @Override
////        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
////            System.out.println("InstantiationAwareBeanPostProcessor接口：postProcessBeforeInstantiation");
////            return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
////        }
//
////        @Override
////        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
////            System.out.println("InstantiationAwareBeanPostProcessor接口：postProcessAfterInstantiation");
////            return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
////        }
//
//        @Override
//        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
//            System.out.println("InstantiationAwareBeanPostProcessor接口：postProcessProperties()");
//            return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
//        }
//    }
    public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    }

    public class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

        @Override
        public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {

        }
    }

}
