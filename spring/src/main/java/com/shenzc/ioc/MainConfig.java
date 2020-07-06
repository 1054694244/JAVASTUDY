package com.shenzc.ioc;

import com.shenzc.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;


/**
 * @author shenzc
 * @create 2020-07-05-17:33
 */
//配置类 == 配置文件

/**
 * excludeFilters：按照特定规则排序哪些bean
 * includeFilters：按照特定规则只要哪些bean
 * FilterType:指定不同类型的过滤器
 */
@Configuration      //告诉spring这是一个配置类
@ComponentScans(value = {
    @ComponentScan(value = "com.shenzc",includeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class, Repository.class}),
            @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFiler.class})
    },useDefaultFilters = false)
})
public class MainConfig {

    //给容器中注册一个Bean，类型为返回值类型，id默认是方法名，也可以指定
    @Bean(value = "YourPerson")
    public Person person(){
        return new Person("张三",13,"小张三");
    }

    @Test
    public void test1(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person1 = applicationContext.getBean(Person.class);
        System.out.println(person1);
        //获取id名
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        Stream.of(beanNamesForType).forEach(System.out::println);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
    }

}
