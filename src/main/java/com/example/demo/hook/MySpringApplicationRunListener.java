package com.example.demo.hook;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

/**
 * @ClassName: MyApplicationListener
 * @Description:
 * @Author: zc
 * @Create: 2025/7/4 13:38
 * @Version: v1.0
 */
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println(">SpringApplicationRunListener：starting");

    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        //environment已设置好 propertySources-属性源 和 profile-生效的配置文件
        System.out.println(">SpringApplicationRunListener：environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        //context已设置好 环境
        System.out.println(">SpringApplicationRunListener：contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        //context仅注册主类BeanDefinition
        System.out.println(">SpringApplicationRunListener：contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        //context已经 refresh() 完成所有BeanDefinition的注册 单例Bean的创建
        System.out.println(">SpringApplicationRunListener：started");
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println(">SpringApplicationRunListener：ready");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println(">SpringApplicationRunListener：failed");
    }
}
