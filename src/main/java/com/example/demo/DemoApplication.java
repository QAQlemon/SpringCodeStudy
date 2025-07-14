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
        //  RunListener
        //      注册和创建SpringApplicationRunListeners
        //          spring的事件广播
        //          自定义应用运行监听器
        //      触发监听器listeners.starting
        //
        //  Environment
        //      通过ApplicationContextFactory创建 可配置环境ConfigurableEnvironment
        //          ConfigurableEnvironment
        //              PropertySource 属性源配置
        //                  SystemProperties 系统属性    -D传递给JVM的参数
        //                  SystemEnvironment 系统环境  操作系统相关的环境变量
        //
        //              Profile        配置文件生效处理
        //      RunListener
        //          environmentPrepared 通过spring内置的事件广播机制将事件广播，最终由EnvironmentPostProcessorApplicationListener去获取
        //          spring.factories中注册好的EnvironmentPostProcessor对环境中的PropertySource进行解析
        //
        //
        //
        //  Banner
        //
        //  context
        //      通过ApplicationContextFactory创建 可配置应用上下文ConfigurableApplicationContext
        //          AnnotationConfigServletWebServerApplicationContext
        //          ServletWebServerApplicationContext
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
