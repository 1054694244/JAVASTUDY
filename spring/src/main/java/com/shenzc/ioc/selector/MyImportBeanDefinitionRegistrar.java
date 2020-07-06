package com.shenzc.ioc.selector;

import com.shenzc.entity.White;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author shenzc
 * @create 2020-07-05-20:12
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param annotationMetadata：当前类的注册信息
     * @param registry：注册类，把所有需要添加到容器中的bean，通过registerBeanDefinition手动注册到容器中
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.shenzc.entity.Color");
        boolean color = registry.containsBeanDefinition("com.shenzc.entity.Red");
        if (red && color){
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(White.class);
            registry.registerBeanDefinition("white",rootBeanDefinition);
        }
    }
}
