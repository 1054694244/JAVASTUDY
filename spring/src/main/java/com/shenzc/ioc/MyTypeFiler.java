package com.shenzc.ioc;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * @author shenzc
 * @create 2020-07-05-18:49
 */
@Repository
public class MyTypeFiler implements TypeFilter {

    /**
     *
     * @param metadataReader:读取到当前正在扫描的类信息
     * @param metadataReaderFactory：可以获取到其他类任何信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类的资源
        Resource resource = metadataReader.getResource();
        return false;
    }
}
