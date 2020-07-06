package com.shenzc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author shenzc
 * @create 2020-07-05-17:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    /**
     * 属性赋值三种方式
     * @Value()
     * string
     * #{spel表达式}
     */

    @Value("张三")
    private String name;

    @Value("#{22-11}")
    private int age;

    @Value("${nikeName}")
    private String nikeName;

}
