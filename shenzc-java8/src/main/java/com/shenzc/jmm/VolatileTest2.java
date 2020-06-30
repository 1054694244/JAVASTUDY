package com.shenzc.jmm;

public class VolatileTest2 {

    /**
     * 保证在多线程中访问共享变量保持一致
     */
    public static volatile int num = 0;

    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[100];
        for (int i=0;i<threads.length;i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<100;i++){
                        add();
                    }
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads){
            thread.join();
        }
        System.out.println(num);
    }

    public static void add(){
        //System.out.println(Thread.currentThread().getName());
        num++;
    }

}
