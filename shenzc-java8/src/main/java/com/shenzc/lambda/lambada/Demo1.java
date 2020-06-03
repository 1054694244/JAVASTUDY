package com.shenzc.lambda.lambada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shenzc
 * @create 2020-06-03-12:25
 */
public class Demo1 {

    //比较学生成绩大于90
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("张三",17,1000),
                new Student("张三",19,2001),
                new Student("张三",18,2005),
                new Student("张三",20,1888),
                new Student("张三",15,3000)
                );

        //List<Student> students = filterStudent(list, new AgeCompare());
        //List<Student> students = filterStudent(list, new SalaryCompare());



        //优化二：匿名内部类
        /*List<Student> students = filterStudent(list, new StudentCompare<Student>() {
            @Override
            public boolean compare(Student student) {
                return student.getAge() > 15;
            }
        });*/


        //优化三：lambada表达式
        List<Student> students = filterStudent(list, student -> student.getAge() > 20);

        StudentCompare<Student> compare = student -> student.getAge()>20;


        //优化四：使用stream流
        //List<Student> students = list.stream().filter(student -> student.getAge() > 15).collect(Collectors.toList());


        students.forEach(System.out::println);

    }

    //优化一：策略设计模式
    public static List<Student> filterStudent(List<Student> list , StudentCompare<Student> compare){
        List<Student> students = new ArrayList<>();
        for (Student student : list){
            if (compare.compare(student)){
                students.add(student);
            }
        }
        return students;
    }



}
