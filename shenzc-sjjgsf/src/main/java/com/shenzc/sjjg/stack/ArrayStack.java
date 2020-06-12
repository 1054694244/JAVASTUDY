package com.shenzc.sjjg.stack;

import java.util.regex.Pattern;

/**
 * @author shenzc
 * @create 2020-06-12-11:30
 */
public class ArrayStack {

    private int[] stack;

    private int top;

    private int maxSize;

    public ArrayStack(int size){
        this.stack = new int[size];
        this.maxSize = size;
    }

    public int size(){
        return top+1;
    }

    public boolean isEmpty(){
        if (size() == 0){
            return true;
        }
        return false;
    }

    public boolean isFull(){
        return top == maxSize-1;
    }

    //pop
    public Integer pop(){
        if (isEmpty()){
            return null;
        }
        return stack[top--];
    }

    //push
    public boolean push(int value){
        //判断是否栈满
        if (isFull()){
            return false;
        }
        stack[++top] = value;
        return true;
    }

    //遍历，从栈顶开始遍历
    public void show(){
        if (isEmpty()){
            return;
        }
        for (int i=top;i>=0;i++){
            System.out.println(stack[top]);
        }
    }

    //判断字符是否为数字
    public static boolean isInteger(Character c) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(c.toString()).matches();
    }

    //判断符号得优先级
    public int priority(Character c){
        if ("-".equals(c) || "+".equals(c)){
            return 1;
        }else if ("*".equals(c) || "/".equals(c) || "%".equals(c)){
            return 2;
        }else if ("(".equals(c) || ")".equals(c)){
            return 3;
        }
        return 0;
    }

    //计算
    public int opreate(int num1, int num2, char opreate){
        switch (opreate){
            case '-': return num1-num2;
            case '+': return num1+num2;
            case '%': return num1%num2;
            case '/': return num1/num2;
            case '*': return num1*num2;
        }
        return 0;
    }

}
