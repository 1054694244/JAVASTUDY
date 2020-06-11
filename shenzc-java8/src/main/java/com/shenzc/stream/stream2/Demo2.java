package com.shenzc.stream.stream2;

import com.shenzc.lambda.lambada.Student;
import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import javax.naming.Name;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shenzc
 * @create 2020-06-11-9:01
 */
public class Demo2 {

    List<Student> list = Arrays.asList(
            new Student("10",17,1000),
            new Student("80",19,2001),
            new Student("290",18,2005),
            new Student("20",20,1888),
            new Student("190",20,30000),
            new Student("90",15,3000)
    );

    public static  <K extends Comparable<? super K>, V > Map<K, V> sortByKey(Map<K,V>map){
        Map<K,V> result = new LinkedHashMap<>();
        LinkedHashMap<K, V> collect = map.entrySet().stream().sorted(
                Map.Entry.<K, V>comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a, //or throw an exception
                        LinkedHashMap::new));
        return collect;
    }

    @Test
    public void test1(){
        Map<String, List<Student>> collect = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.groupingBy(Student::getName));
        Map<String, List<Student>> stringListMap = sortByKey(collect);
        for(String key:stringListMap.keySet()){
            System.out.println(key);
        }
        //list.stream().sorted(Comparator.comparing(x -> Integer.valueOf(x.getName()))).collect(Collectors.toList(),TreeMap::new);
        /*Map<String, List<Student>> collect = list.stream().collect(Collectors.groupingBy(Student::getName));
        Map<String, List<Student>> stringListMap = sortByKey(collect);
        for(Map.Entry<String, List<Student>> key:stringListMap.entrySet()){
            System.out.println(key);
        }*/
    }


}
