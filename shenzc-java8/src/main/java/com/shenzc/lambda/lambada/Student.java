package com.shenzc.lambda.lambada;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author shenzc
 * @create 2020-06-03-12:25
 */
@Data
@AllArgsConstructor
public class Student {

    private String name;

    private int age;

    private int salary;

    public Student(){}

    public Student(String name){
        this.name = name;
    }

    public Student(int age){
        this.age= age;
    }

}
