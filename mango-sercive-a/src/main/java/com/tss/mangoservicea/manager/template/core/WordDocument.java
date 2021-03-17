package com.tss.mangoservicea.manager.template.core;

import com.tss.mangoservicea.manager.template.enums.DocumentType;
import com.tss.mangoservicea.manager.template.handler.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

/**
 * Created by yangxiangjun on 2021/2/3.
 */
@Component
public class WordDocument implements DocumentGenerator {
    @Override
    public DocumentType getType() {
        return DocumentType.WORD;
    }

    @Override
    public File generateFile(Map<String, Object> dataMap, String sourceFilePath, String targetFilePath) {
        DocExecutor builder = new DocExecutor(dataMap,sourceFilePath,targetFilePath);
        builder.addChain(new AnalysisParamHandler())
                .addChain(new NormalizationHandler())
                .addChain(new SupplementHandler())
                .addChain(new XMLWriterHandler())
                .addChain(new XmlToFtlHandler());
        File build = builder.build();
        return build;
    }
}
