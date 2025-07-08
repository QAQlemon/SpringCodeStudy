package com.example.demo;

import com.example.demo.config.HookConfiguration;
import com.example.demo.config.ProfileConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
//@EnableConfigurationProperties
public class DemoApplication {

    public static void main(String[] args) {
        //1.创建SpringApplication
        //  多个启动类primarySources
        //  推导应用类型  Reactive或Servlet
        //  注册  BootstrapRegistryInitializer   均从spring.factories文件中注册
        //  注册  ApplicationContextInitializer
        //  注册  ApplicationListener
        //  推导main方法所在启动类实例
//        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder();
//        springApplicationBuilder
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
//        springApplication.addBootstrapRegistryInitializer();//BootstrapRegistryInitializer
//        springApplication.setInitializers();//ContextInitializer
//        springApplication.setListeners();//监听器

        //2.运行
        //  创建BootstrapContext  用于创建ApplicationContext前提供一个上下文环境
        //      调用容器拓展点BootstrapRegistryInitializer.initialize()
        //
        //  listener
        //      创建SpringApplicationRunListeners
        //      触发监听器listeners.starting
        //
        //  Environment
        //      基于BootstrapContext创建可用环境
        //          PropertySource 属性源配置
        //          Profile        配置文件生效处理
        //      binder 属性源
        //      listener
        //          environmentPrepared
        //      Binder
        //          绑定Environment与SpringApplication
        //
        //  Banner
        //
        //  context
        //      create
        //      prepare
        //          ApplicationContextInitializer.initialize()
        //
        //          listener
        //              contextPrepared
        //      refresh
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        ProfileConfig bean1 = applicationContext.getBean(ProfileConfig.class);
        HookConfiguration bean2 = applicationContext.getBean(HookConfiguration.class);
        System.out.println(bean1);
        System.out.println(bean2);

    }

}
