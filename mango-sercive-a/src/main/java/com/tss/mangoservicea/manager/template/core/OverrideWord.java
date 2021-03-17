package com.tss.mangoservicea.manager.template.core;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

/**
 * Created by yangxiangjun on 2021/2/3.
 */
@Component
public class OverrideWord extends WordDocument{
    @Override
    public File generateFile(Map<String, Object> dataMap, String sourceFilePath, String targetFilePath) {
        System.out.println("789");
        return null;
    }
}
