package com.shenzc.lambda.lambada;

/**
 * @author shenzc
 * @create 2020-06-03-13:18
 */
public class AgeCompare implements StudentCompare<Student> {

    @Override
    public boolean compare(Student student) {
        return student.getAge()>18;
    }
}
