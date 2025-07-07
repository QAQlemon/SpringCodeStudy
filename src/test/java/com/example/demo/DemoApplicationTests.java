package com.example.demo;

import com.example.demo.hook.BeanDemo;
import com.example.demo.hook.MyApplicationContextInitializer;
import com.example.demo.hook.SpringLifeHook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

interface Base{
    default public int func(int a){
        return a;
    }
}
class B implements Base{

    @Override
    public int func(int a ) {
        return a;
    }
}
class A{
    public void  func(BiFunction<Base, Integer, Integer> action, int arg){
        B b = new B();
        Object res = action.apply(b,arg);
    }
}


//@SpringBootTest
class DemoApplicationTests {
    public static void main(String[] args) {
        A a = new A();
//        BiFunction<Base,Integer,Integer> f = Base::func;

        a.func(Base::func,1);
    }

    @Test
    void contextLoads() {

    }
}

