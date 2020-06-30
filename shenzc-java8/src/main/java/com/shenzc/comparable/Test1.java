package com.shenzc.comparable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

    @Test
    public void test1(){
        List<MyComparable1> list = new ArrayList<>();
        MyComparable1 m4 = new MyComparable1(50,"小四");
        MyComparable1 m2 = new MyComparable1(8,"小二");
        MyComparable1 m3 = new MyComparable1(40,"小三");
        MyComparable1 m1 = new MyComparable1(10,"小一");
        System.out.println(m1.compare(m1,m2));
    }

}
