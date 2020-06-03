package com.shenzc.lambda.lambda2;

/**
 * @author shenzc
 * @create 2020-06-03-14:17
 */
@FunctionalInterface
public interface MathInterface {

    public int get(int i);

    public static int getValue(int i , MathInterface math){
        return math.get(i);
    }

}
