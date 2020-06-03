package com.shenzc.lambada;

/**
 * @author shenzc
 * @create 2020-06-03-13:30
 */
public class SalaryCompare implements StudentCompare<Student>{
    @Override
    public boolean compare(Student student) {
        return student.getSalary()>2000;
    }
}
