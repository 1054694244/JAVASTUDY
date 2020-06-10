package com.shenzc.sjjg.queue;

import org.junit.Test;

import java.util.List;

/**
 * @author shenzc
 * @create 2020-06-10-10:50
 */
public class CircularQueue {

    private int front;

    private int rear;

    private int maxSize;

    private int[] arr;

    public CircularQueue(int size){
        //头指针默认指向0
        front = 0;
        //尾指针默认指向0
        rear  = 0;
        maxSize = size;
        arr = new int[size];
    }

    //环形队列是否已经满了
    public boolean isFull(){
        if ((rear+1)%maxSize == front){
            return true;
        }else {
            return false;
        }
    }

    //是否为空
    public boolean isEmpty(){
        if (rear == front){
            return true;
        }else {
            return false;
        }
    }

    //获取队列长度
    public int size(){
        return (rear - front + maxSize)%maxSize;
    }

    //添加数据
    public boolean add(int obj){
        if (isFull()){
            System.out.println("队列已满");
            return false;
        }else {
            //将尾指针往后移动一位
            arr[rear++] = obj;
            if (rear == maxSize){
                rear = 0;
            }
            return true;
        }
    }

    //移除数据
    public Integer get(){
        if (isEmpty()){
            return null;
        }else {
            int value = arr[front++];
            if(front == maxSize){
                front = 0 ;
            }
            return value;
        }
    }

}
