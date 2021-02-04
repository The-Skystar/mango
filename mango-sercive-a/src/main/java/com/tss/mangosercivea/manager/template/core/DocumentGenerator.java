package com.tss.mangosercivea.manager.template.core;

import com.tss.mangosercivea.manager.template.enums.DocumentType;

import java.io.File;
import java.util.Map;

/**
 * Created by yangxiangjun on 2021/1/29.
 */
public interface DocumentGenerator {
    DocumentType getType();

    File generateFile(Map<String, Object> dataMap, String sourceFilePath, String targetFilePath);
}
