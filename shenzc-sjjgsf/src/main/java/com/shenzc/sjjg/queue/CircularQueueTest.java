package com.shenzc.sjjg.queue;

import org.junit.Test;

/**
 * @author shenzc
 * @create 2020-06-10-11:16
 */
public class CircularQueueTest {
    @Test
    public void test1(){
        CircularQueue queue = new CircularQueue(5);
        queue.add(5);
        queue.add(12);
        queue.add(31);
        queue.add(31);
        queue.add(31);
        System.out.println(queue.get());
        queue.add(31);
        System.out.println(queue.size());
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        queue.add(32);
        queue.add(33);
        System.out.println(queue.size());
    }
}
