package com.shenzc.sjjg.stack;

import java.util.regex.Pattern;

/**
 * @author shenzc
 * @create 2020-06-12-11:19
 */
public class MathDemo {

    public static void main(String[] args) {
        System.out.println(math("112+2/2"));
    }

    //中缀表达式
    public static Integer math(String str){
        if (str.length() == 0){
            return null;
        }
        //去除空格
        str = str.trim();
        char[] chars = str.toCharArray();
        //创建两个栈，一个存放数字，一个存放符号
        ArrayStack intArr = new ArrayStack(10);
        ArrayStack2 strArr = new ArrayStack2(10);
        int sum = 0;
        int index = 0;
        String string = "";
        while (index < str.length()){
            char c = str.charAt(index);
            if (intArr.isInteger(c)){
                string += c;
                if (index == str.length()-1){
                    intArr.push(Integer.valueOf(string));
                    string = "";
                }else if (!intArr.isInteger(str.charAt(index+1))){
                    intArr.push(Integer.valueOf(string));
                    string = "";
                }
                index++;
            }else {
                if (strArr.isEmpty()){
                    strArr.push(c);
                }else if (strArr.priority(c) <= strArr.priority(strArr.pop())){
                    sum += intArr.opreate(intArr.pop(), intArr.pop(), strArr.pop());
                    intArr.push(sum);
                    strArr.push(c);
                }else if (strArr.priority(c) == 3){
                    while (strArr.priority(c) != 0){
                        sum += intArr.opreate(intArr.pop(), intArr.pop(), strArr.pop());
                        intArr.push(sum);
                    }
                }else {
                    strArr.push(c);
                }
                index++;
            }
        }
        while (intArr.size() > 1){
            sum += intArr.opreate(intArr.pop(), intArr.pop(), strArr.pop());
            intArr.push(sum);
        }
        return sum;
    }



}
