package com.example.demo.hook;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyApplicationContextInitializer
 * @Description:
 * @Author: zc
 * @Create: 2025/7/4 13:36
 * @Version: v1.0
 */
//@Component
public class MyApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("==>ApplicationContextInitializer接口：initialize");
    }
}
