package com.example.csvinxml.processor.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextWrapper {
    private static ApplicationContext context;

    public static ApplicationContext getContext(){
        if (context == null){
            context = new ClassPathXmlApplicationContext("contexts/csvinxml-bootstrap.xml");
        }
        return context;
    }

}
