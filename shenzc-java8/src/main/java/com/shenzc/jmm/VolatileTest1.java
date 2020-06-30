package com.shenzc.jmm;

public class VolatileTest1 {

    /**
     * 保证在多线程中访问共享变量保持一致
     */
    public static volatile boolean initFlat = false;

    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("启动开始");
                while (!initFlat){

                }
                System.out.println("启动完毕");
            }
        }).start();

        Thread.sleep(2000);

        new Thread(() -> {
            System.out.println("我开始了");
            initFlat = true;
            System.out.println("我结束了");
        }).start();
    }

}
