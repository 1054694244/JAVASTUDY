package com.shenzc.ioc;

/**
 * @author shenzc
 * @create 2020-07-06-8:15
 */

import com.shenzc.entity.Car;
import com.shenzc.entity.Carrot;
import com.shenzc.entity.Cat;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

/**
 * bean的生命周期
 *  bean的创建---初始化---销毁的过程
 *  容器管理bean的生命周期
 *  我们可以自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 *  指定初始化销毁方法
 *  1：通过@Bean指定init-method和destory-method
 *  2：通过Bean实现InitializingBean和DisposableBean接口
 *  3：可以使用JR250：
 * @PostConstruct：在构造之后调用
 * @PreDestory：在销毁之前调用
 *  3：BeanPostProcessor：bean的后置处理器，在bean的初始化前后进行一些处理工作
 *      postProcessBeforeInitialization：初始化之前
 *      postProcessAfterInitialization：初始化之后
 */
@Configuration
@ComponentScan(value = "com.shenzc.ioc")
public class MainConfigLifyCycle {

    @Bean(initMethod = "init",destroyMethod = "destory")
    public Car car(){
        return new Car();
    }

    @Bean()
    public Cat cat(){
        return new Cat();
    }

    @Bean()
    public Carrot carrot(){
        return new Carrot();
    }



    @Test
    public void test(){
        //1.容器创建完成
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigLifyCycle.class);
        System.out.println("容器创建完成");

        //容器关闭
        applicationContext.close();
    }

}
