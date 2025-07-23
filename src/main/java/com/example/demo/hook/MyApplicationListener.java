package com.example.demo.hook;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @ClassName: MyApplicationListener
 * @Description:
 * @Author: zc
 * @Create: 2025/7/8 21:38
 * @Version: v1.0
 */
public class MyApplicationListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("\nApplicationListener接口：onApplicationEvent(),"+event.toString());
    }
}
