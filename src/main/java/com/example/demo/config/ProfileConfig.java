package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName: Config
 * @Description:
 * @Author: zc
 * @Create: 2025/7/7 16:51
 * @Version: v1.0
 */
@ToString
@Setter
@Getter
@Configuration
//@PropertySource("classpath:customProperties/BeanDemoCfg.properties")//注：资源只需在一处引入，就可以在其他地方进行引用
//注：springboot启动时会自动搜索 classpath 或 classpath/config 下的application.properties和application.yml
@ConfigurationProperties(prefix = "bean-data")//必须使用-分隔符，不能beanData
public class ProfileConfig {
//    @Value("${server.port}")
    Integer port;

//    @Value("${profile}")
    String profile;

//    @Value("${beanData.name}")
    String name;

//    @Value("${bean-data.age}")
    Integer age;

}
