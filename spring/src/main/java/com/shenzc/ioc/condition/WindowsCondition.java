package com.shenzc.ioc.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author shenzc
 * @create 2020-07-05-19:24
 */
public class WindowsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String os = environment.getProperty("os.name");
        if (os.toUpperCase().contains("windows".toUpperCase())){
            return true;
        }
        return false;
    }
}
