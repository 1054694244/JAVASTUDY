package com.shenzc.sjjg.queue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author shenzc
 * @create 2020-06-09-17:32
 */
public class ArrayQueue {

    /**
     * 队列最大值
     */
    private int maxSize;

    /**
     * 队列头
     */
    private int front;

    /**
     * 队列尾
     */
    private int rear;

    /**
     * 队列存放的数据
     */
    private int arr[];

    //初始化
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        //当前队列没有数据，索引指定-1；
        front = -1; //指向队列头部的位置
        rear  = -1; //指向队列尾部的位置
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public boolean add(int n){
        if (isFull()){
            return false;
        }else {
            rear++;
            arr[rear] = n;
            return true;
        }
    }

    //获取数据
    public Integer get(){
        if (isEmpty()){
            return null;
        }else {
            //front是处于队列当前元素的前一个索引值，所以需要++
            front++;
            return arr[front];
        }
    }

    //展现数据
    public List<Integer> show(){
        if (isEmpty()){
            return null;
        }else {
            List<Integer> list = new ArrayList(Arrays.asList(arr));
            return list;
        }
    }

    @Test
    public void test1(){

    }

}


