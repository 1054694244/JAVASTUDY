package com.shenzc.stream.stream2;

/**
 * @author shenzc
 * @create 2020-06-03-20:58
 */

import com.shenzc.lambda.lambada.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 筛选与切片
 * filter——接受lambda，从流中排除元素
 * limit——截断流，使其元素不超过给定数量。
 * skip(n)——跳过元素，返回一个扔掉了前n个元素的流程，若流中元素不足n个，则返回一个空流。与limit(n)互补
 * distinct——筛选，通过流所生成元素的hashCode()和equals()去除重复元素
 */
public class Demo1 {

    List<Student> list = Arrays.asList(
            new Student("张三",17,1000),
            new Student("张三",19,2001),
            new Student("张三",18,2005),
            new Student("张三",20,1888),
            new Student("张三",20,1888),
            new Student("张三",15,3000)
    );

    @Test
    public void test(){
        //内部迭代：迭代操作由Stream的API完成
        Stream<Student> stream = list.stream()
                .filter(student -> {
                    System.out.println("我是谁");
                    return student.getAge()>18;
                });

        //终止操作：一次性执行全部内容，即惰性求值
        stream.forEach(System.out::println);
    }

    @Test
    public void test2(){
        //找到了所需要的数据，就不会迭代后面的数据，能提高性能
        list.stream()
                .filter(student -> {
                    System.out.println("短路");
                    return student.getAge()>17;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test3(){
        list.stream()
                .filter(student -> {
                    System.out.println("短路");
                    return student.getAge()>17;
                })
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        list.stream()
                .filter(student -> {
                    //System.out.println("短路");
                    return student.getAge()>10;
                })
                .distinct()
                .forEach(System.out::println);
    }

    public static Stream<Character> getCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character character:str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }


    /**
     * 映射
     * map——接受lambda，将元素转换成其他形式或提取信息。接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap——接受一个函数作为参数，将流中的每个值都·换成另一个流，然后把所有的流连接成一个流。
     */

    @Test
    public void test5(){
        List<String> arrays = Arrays.asList("aaa","bbb","ccc");
        /*Stream<String> stream = arrays.stream()
                .map(l -> l.toUpperCase());
        stream.forEach(System.out::println);*/

        /*list.stream()
                .map(Student::getName)
                .forEach(System.out::println);*/

        //map将每个流添加到一个流里面{{1，2，3}，{1，2，3}}
        Stream<Stream<Character>> streamStream = arrays.stream()
                .map(Demo1::getCharacter);
        //streamStream.forEach( sm -> sm.forEach(System.out::println));

        //flatMap将流里的一个个数据添加到一个新的流{1，2，3，1，2，3}
        Stream<Character> characterStream = arrays.stream()
                .flatMap(Demo1::getCharacter);
        characterStream.forEach(System.out::println);

    }


    //排序
    /**
     * sorted()——自然排序(comparable)
     * sorted(Comparator com)——定制排序
     */
    @Test
    public void test6(){
        List<String> arrays = Arrays.asList("aaa","bbb","ccc");
        /*arrays.stream()
                .sorted()
                .forEach(System.out::println);*/

        list.stream()
                .sorted((a,b) -> {
                    if (a.getAge() == b.getAge()){
                        return a.getName().compareTo(b.getName());
                    }else {
                        return String.valueOf(a.getAge()).compareTo(String.valueOf(b.getAge()));
                    }
                })
                .forEach(System.out::println);

    }

}
