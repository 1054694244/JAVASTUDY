package com.shenzc.ioc;

import com.shenzc.entity.Color;
import com.shenzc.entity.Person;
import com.shenzc.entity.Red;
import com.shenzc.ioc.condition.LinuxCondition;
import com.shenzc.ioc.condition.WindowsCondition;
import com.shenzc.ioc.factory.MyFactory;
import com.shenzc.ioc.selector.MyImportBeanDefinitionRegistrar;
import com.shenzc.ioc.selector.MyImportSelector;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author shenzc
 * @create 2020-07-05-17:33
 */
@Configuration
@PropertySource(value = {"classpath:/person.properties"})
public class MainConfig2 {

    @Bean
    public Person person(){
        return new Person();
    }



    @Test
    public void test1(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        Person person1 = (Person)applicationContext.getBean("person");
        System.out.println(person1);
    }


}
