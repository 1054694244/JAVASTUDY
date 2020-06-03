package com.shenzc.lambda2;

/**
 * @author shenzc
 * @create 2020-06-03-13:53
 */
public class Demo1 {

    /**
     * 1：没有参数，无返回值：
     *  () -> System.out.println("123")
     *
     * 2：只有一个参数，无返回值（小括号可以不写）
     *  (x) -> System.out.println("123")
     *
     * 3：又两个参数，有返回值
     *  Compare<Integer> com = (x,y) -> {
     *      System.out.println("123")
     *      return Integer.compare(x,y)
     *  }
     */

    public static void main(String[] args) {
        int value = MathInterface.getValue(10, x -> x * x);
        System.out.println(value);

        MathInterface math = x -> x*x;
        System.out.println(math.get(10));
    }


}
