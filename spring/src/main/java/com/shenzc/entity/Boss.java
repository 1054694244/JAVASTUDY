package com.shenzc.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shenzc
 * @create 2020-07-05-17:34
 */
@Data
@Component
public class Boss {

    private Car car;

    @Autowired
    public Boss(Car car){
        this.car = car;
    }

    public Boss(){

    }

    //@Autowired
    //标注在方法上，Spring容器创建当前对象，就会调用此方法，完成赋值
    //方法使用的参数，自定义类型的值就ioc容器中取
    public void setCar(Car car){
        this.car = car;
    }

    public Car getCar(){
        return this.car;
    }

}
