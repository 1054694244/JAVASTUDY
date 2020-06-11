package com.shenzc.stream.steam1;

/**
 * @author shenzc
 * @create 2020-06-03-18:36
 */

import com.shenzc.lambda.lambada.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *   1:stream的三个操作步骤
 *
 *   1：创建Stream
 *   2：进行中间一系列的处理操作
 *   3：终止操作
 */
public class Demo1 {

    //创建Stream
    @Test
    public void test1(){
        //1：可以通过Collection系列提供的Stream()或者parallelStream()方法
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2：通过Arrays的静态方法stream()方法获取数组流
        Student[] students = new Student[10];
        Stream<Student> stream2 = Arrays.stream(students);

        //3：通过Stream的静态方法of()获取流
        Stream<String> stream3 = Stream.of("123", "123", "456");

        //4：创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);
        //生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(10).forEach(System.out::println);
    }
}
