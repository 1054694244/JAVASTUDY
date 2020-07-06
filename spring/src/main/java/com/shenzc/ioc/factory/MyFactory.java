package com.shenzc.ioc.factory;

import com.shenzc.entity.Color;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Repository;

/**
 * @author shenzc
 * @create 2020-07-05-20:19
 */
//创建一个spring定义的FactoryBean
public class MyFactory implements FactoryBean<Color> {

    //返回一个Color对象，这个对象会添加到容器中
    @Override
    public Color getObject() throws Exception {

        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * true:单实例
     * false：多实例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
