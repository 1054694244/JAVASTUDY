package com.shenzc.comparable;

import java.math.BigDecimal;
import java.util.Comparator;

public class MyComparable1 implements Comparator<MyComparable1> {

    private int age;

    private BigDecimal monty;

    private String name;

    public MyComparable1(int age, String name){
        this.age = age;
        this.name = name;
    }



    @Override
    public String toString() {
        return name+"---"+age;
    }

    /**
     * 实现comparator接口，自定义排序（从大到小排序）。
     * @param o
     * @return
     */
    @Override
    public int compare(MyComparable1 o1, MyComparable1 o2) {
        if (o1.age>o2.age){
            return 1;
        }else if (o2.age == o1.age){
            return 0;
        }else {
            return -1;
        }
    }
}
