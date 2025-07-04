package com.example.demo;

import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

/**
 * @ClassName: MyBootstrapRegistryInitializer
 * @Description:
 * @Author: zc
 * @Create: 2025/7/4 15:09
 * @Version: v1.0
 */
public class MyBootstrapRegistryInitializer implements BootstrapRegistryInitializer {
    @Override
    public void initialize(BootstrapRegistry registry) {
        System.out.println("==>BootstrapRegistryInitializer接口：initialize");
    }
}
