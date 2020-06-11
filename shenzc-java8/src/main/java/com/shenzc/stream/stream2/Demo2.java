package com.shenzc.stream.stream2;

/**
 * @author shenzc
 * @create 2020-06-07-8:55
 */

import com.shenzc.lambda.lambada.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 查找与匹配
 * allMatch——检查是否匹配所有元素
 * anyMatch——检查是否至少匹配一个元素
 * noneMatch——检查是否没有匹配所有元素
 * findFirst——返回第一个元素
 * findAny——返回当前流中的任意元素、
 * count——返回流中元素的总个数
 * max——返回流中最大值
 * min——返回流中最小是
 */
public class Demo2 {

    List<Student> list = Arrays.asList(
            new Student("张三",17,1000),
            new Student("张三",19,2001),
            new Student("张三",18,2005),
            new Student("张三",20,1888),
            new Student("张三",20,1888),
            new Student("张三",15,3000)
    );

    @Test
    public void test1(){
        boolean zs1 = list.stream()
                .allMatch(a -> a.getName().equals("张三"));
        System.out.println(zs1);

        boolean zs2 = list.stream()
                .anyMatch(a -> a.getName().equals("张三"));
        System.out.println(zs2);

        boolean zs3 = list.stream()
                .noneMatch(a -> a.getName().equals("张三"));
        System.out.println(zs3);

        Optional<Student> first = list.stream()
                .sorted((a, b) -> String.valueOf(a.getAge()).compareTo(String.valueOf(b.getAge())))
                .findFirst();
        System.out.println(first.get());

        Optional<Student> first1 = list.stream()
                .filter(a -> a.getName().equals("张三"))
                .findAny();
        System.out.println(first1.get());

        long count = list.stream()
                .count();
        System.out.println(count);

        Optional<Student> max = list.stream()
                .max((a, b) -> Integer.compare(a.getAge(), b.getAge()));
        System.out.println(max.get());

        Optional<Student> min = list.stream()
                .min((a, b) -> Integer.compare(a.getAge(), b.getAge()));
        System.out.println(min.get());

    }


    /**
     * 归约
     * reduce(T identity,BinaryOperator)/reduce(binaryOperator)——可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test2(){
        Integer reduce = list.stream()
                .map(x -> x.getSalary())
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        Optional<Integer> reduce1 = list.stream()
                .map(x -> x.getSalary())
                .reduce((x,y) -> x+y);
        System.out.println(reduce1.get());
    }


    /**
     * 收集
     * collect——将流转换为其他形式，接受一个Collector接口的实现，用于给Stream中的元素汇总的方法
     */
    @Test
    public void test3(){
        /*List<Student> collect = list.stream()
                .collect(Collectors.toList());

        HashSet<Student> collect1 = list.stream()
                .collect(Collectors.toCollection(HashSet::new));

        //总数
        Collectors.counting();

        //平均值
        Collectors.averagingInt(p -> 1);

        //总数
        Collectors.summingInt(p -> 1);

        //最大值
        Collectors.maxBy((x,y) -> Double.compare(1,2));

        //分组
        Collectors.groupingBy(x -> x);

        IntSummaryStatistics collect2 = list.stream()
                .collect(Collectors.summarizingInt(p -> p.getAge()));
        collect2.getMax();
        collect2.getAverage();*/

        String collect = list.stream()
                .map(student -> student.getName())
                .collect(Collectors.joining(",", "____", "_____"));
        System.out.println(collect);

    }


}
