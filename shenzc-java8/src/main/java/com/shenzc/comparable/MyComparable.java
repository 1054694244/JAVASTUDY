package com.shenzc.comparable;

import java.math.BigDecimal;

public class MyComparable implements Comparable<MyComparable>{

    private int age;

    private BigDecimal monty;

    private String name;

    public MyComparable(int age,String name){
        this.age = age;
        this.name = name;
    }

    /**
     * 实现comparable接口，自定义排序（从大到小排序）。
     * @param o
     * @return
     */
    @Override
    public int compareTo(MyComparable o) {
        if (this.age>o.age){
            return 1;
        }else if (this.age==o.age){
            return 0;
        }else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return name+"---"+age;
    }

}
