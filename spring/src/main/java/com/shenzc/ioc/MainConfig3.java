package com.shenzc.ioc;

import com.shenzc.entity.Boss;
import com.shenzc.entity.Car;
import com.shenzc.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * @author shenzc
 * @create 2020-07-05-17:33
 */

/**
 * 1：@Autowired:自动注入
 *  1：默认按照类型去寻找对应的组件getBean(BookDao.class)
 *  2：如果找到类型相同的组件，在将属性的名称作为id去容器中寻找getBean("bookDao")
 *  3：@Qualifier("bookDao")：指定需要自动装配的id，而不是使用属性名，自动装配一定要将属性赋值好，没有就会报错，可以使用@Autowire(required=false)
 *  4：@Primary：优先指定自动导入的是哪一个bean
 *
 * 2：@Resource：
 *      可以和@Autowired一样实现自动装配工能，默认是按照组件id名称去装配getBean("bookDao")
 *      不支持@Primary和@autowire(required = false);
 * 3：@Inject
 *      和@autowired功能一样，不过没有required = false工能
 *
 *
 * 4：@Autowired：构造器，方法，参数，属性
 */
@Configuration
@ComponentScan("com.shenzc.entity")
public class MainConfig3 {

    @Bean
    public Car car(){
        return new Car();
    }





    @Test
    public void test1(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig3.class);
        Car car = (Car)applicationContext.getBean("car");
        Boss boss = (Boss)applicationContext.getBean("boss");
        System.out.println(car);
        System.out.println(boss);
        System.out.println(car == boss.getCar());
    }


}
