package com.tss.mangoservicea.manager.template.core;

import com.tss.mangoservicea.manager.template.enums.DocumentType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by yangxiangjun on 2021/2/3.
 */
@Component
public class DocumentFactory implements ApplicationContextAware {
    private List<DocumentGenerator> generators;

    public DocumentGenerator createGenerator(DocumentType documentType){
        Optional<DocumentGenerator> generatorOptional = generators.stream().filter(generator -> documentType.equals(generator.getType())).findFirst();
        if (generatorOptional.isPresent()) {
            return generatorOptional.get();
        } else {
            throw new RuntimeException("找不到对应的文档处理器");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        generators = new ArrayList<>();
        Map<String, DocumentGenerator> beansOfType = applicationContext.getBeansOfType(DocumentGenerator.class);
        generators.addAll(beansOfType.values());
    }
}
