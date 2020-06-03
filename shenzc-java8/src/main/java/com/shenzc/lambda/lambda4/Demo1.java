package com.shenzc.lambda.lambda4;

import com.shenzc.lambda.lambada.Student;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author shenzc
 * @create 2020-06-03-17:49
 */
public class Demo1 {

    /**
     * 方法引用：若lambda体中的内容有方法已经实现，我们可以使用“方法引用：
     *          （可以理解为方法引用就是lambda表达式的另一中表现形式）
     *
     * 主要有三种语法格式：
     * 1：对象::实例方法名
     * 2：类::静态方法名
     * 3：类::实例方法名
     *
     *  注意：
     *  1：Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值要保持一致
     *  2：若lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
     *
     *
     *  构造器引用：
     *  格式：
     *  CLassName::new
     *
     *
     *  数组引用
     *  Type::new
     */

    @Test
    public void test1(){
        PrintStream ps1 = System.out;
        Consumer<String> consumer = (x) -> ps1.println(x);
        PrintStream ps = System.out;
        Consumer<String> consumer1 = ps::println;
        consumer1.accept("你好");
    }

    @Test
    public void test2(){
        Student student = new Student("张三",18,20000);
        Supplier<String> sup = () -> student.getName();

        Supplier<String> sup1 = student::getName;
        System.out.println(sup1.get());

    }


    @Test
    public void test3(){
        Comparator<Integer> com  = (x, y) -> Integer.compare(x,y);

        Comparator<Integer> com1 = Integer::compare;

        System.out.println(com1.compare(1,2));
    }

    @Test
    public void test4(){
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);
        BiPredicate<String,String> bp2 = String::equals;
        System.out.println(bp2.test("123","123"));
    }


    @Test
    public void test5(){
        Supplier<Student> sup1 = Student::new;
        System.out.println(sup1.get());
    }

    @Test
    public void test6(){
        Function<String,Student> fun1 = Student::new;
        System.out.println(fun1.apply("张三"));
    }

    @Test
    public void test7(){
        Function<Integer,Student> function = Student::new;
        System.out.println(function.apply(123));
    }


    @Test
    public void test8(){
        Function<Integer,String[]> function1 = (x) -> new String[x];
        System.out.println(function1.apply(10).length);

        Function<Integer,String[]> function2 = String[]::new;
        System.out.println(function2.apply(20).length);
    }

}
