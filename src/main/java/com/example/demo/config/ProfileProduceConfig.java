package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
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
@PropertySource(
//    "classpath:application.yml"
        "classpath:BeanDemoCfg.yml"
)
//@ConfigurationProperties(prefix = "test")
public class ProfileProduceConfig {
    Integer port;

    String name;

    @Value("${age}")
    Integer age;
}
