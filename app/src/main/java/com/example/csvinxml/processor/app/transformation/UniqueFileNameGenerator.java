package com.example.csvinxml.processor.app.transformation;

import org.springframework.stereotype.Component;

@Component
public class UniqueFileNameGenerator {
    public String generate(){
        return String.valueOf(System.currentTimeMillis());
    }

}
