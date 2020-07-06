package com.shenzc.ioc.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Repository;

/**
 * @author shenzc
 * @create 2020-07-05-19:41
 */
//自定义逻辑返回需要导入的组件
@Repository
public class MyImportSelector implements ImportSelector {

    /**
     *  返回值就是导入到容器中的组件全类名
     * @param importingClassMetadata：当前@Import注解类的所有注解信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{"com.shenzc.entity.Black"};
    }
}
