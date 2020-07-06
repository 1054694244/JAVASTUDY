package com.shenzc.entity;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author shenzc
 * @create 2020-07-05-17:34
 */
@Data
public class Carrot {

    public Carrot(){
        System.out.println("创建Carrot");
    }


    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("销毁Carrot");
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化Carrot");
    }
}
