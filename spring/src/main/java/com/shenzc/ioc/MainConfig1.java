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
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Stream;


/**
 * 给容器注册组件的方式
 * 1：包扫描+组件标注注解
 * 2：@Bean【导入第三方包里的类】
 * 3：@Import【快速给容器中的导入一个组件，id默认为组件的全类名】
 *      ImportSelector
 *      ImportBeanDefinitionRegistrar
 * 4：使用spring提供的FactoryBean（工厂Factory）
 */


/**
 * @author shenzc
 * @create 2020-07-05-17:33
 */
@Configuration      //告诉spring这是一个配置类
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})    //id默认为组件的全类名
public class MainConfig1 {



    /**
     * prototype：多实例：ioc容器启动并不会调用方法创建对象放在容器中，每次获取的时候才会调用此方法
     * singleton：单实例（默认）ioc容器启动会调用方法创建对象放在ioc容器中，以后每次获取就是直接从容器中拿
     * 懒加载；容器启动的时候不调用对象，在第一次调用的时候才创建对象
     * request：同一个请求创建一个实例
     * session：同一个session创建一个实例
     * @return
     */
    @Scope(value = "prototype")
    @Lazy
    @Bean()
    public Person person(){
        //System.out.println("调用此方法");
        return new Person("张三",13,"校长三");
    }

    /**
     * condition：按照一定条件给容器中注册bean
     * 放在bean上，只生效当前bean
     * 放在类上，这个类中所有的bean才会生效
     * @return
     */
    @Conditional(WindowsCondition.class)
    @Bean("lisi")
    public Person person02(){
        //System.out.println("调用此方法");
        return new Person("李四",15,"肖利斯");
    }

    @Conditional(LinuxCondition.class)
    @Bean("wangwu")
    public Person person03(){
        //System.out.println("调用此方法");
        return new Person("王五",25,"肖利斯");
    }

    @Bean
    public MyFactory myFactory(){
        return new MyFactory();
    }

    @Test
    public void test1(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig1.class);
        Person person1 = (Person)applicationContext.getBean("person");
        Person person2 = (Person)applicationContext.getBean("person");
        System.out.println(person1 == person2);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
    }

    @Test
    public void test2(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig1.class);
        //获取运行环境的值
        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        Stream.of(beanNamesForType).forEach(System.out::println);
        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);
    }

    @Test
    public void test3(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig1.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
    }
}
