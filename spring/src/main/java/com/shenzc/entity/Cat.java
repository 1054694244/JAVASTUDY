package com.shenzc.entity;

import javafx.fxml.Initializable;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author shenzc
 * @create 2020-07-05-17:34
 */
@Data
public class Cat implements InitializingBean, DisposableBean {

    public Cat(){
        System.out.println("创建Cat");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("销毁Cat");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化Cat");
    }
}
