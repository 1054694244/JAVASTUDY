package com.shenzc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shenzc
 * @create 2020-07-05-17:34
 */
@Data
public class Car {

    public Car(){
        System.out.println("创建Car");
    }

    public void init(){
        System.out.println("初始化car");
    }

    public void destory(){
        System.out.println("销毁car");
    }

}
