package com.example.demo.hook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @ClassName: MyEnvironmentPostProcessor
 * @Description:
 * @Author: zc
 * @Create: 2025/7/14 21:51
 * @Version: v1.0
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("EnvironmentPostProcessor接口：postProcessEnvironment()");
    }
}
