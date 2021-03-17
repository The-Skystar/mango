package com.tss.mangoservicea;

import com.tss.mangoservicea.manager.template.core.DocumentFactory;
import com.tss.mangoservicea.manager.template.core.DocumentGenerator;
import com.tss.mangoservicea.manager.template.enums.DocumentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiangjun on 2021/2/3.
 */
@SpringBootTest
public class WordExportTest {

    @Autowired
    DocumentFactory documentFactory;

    @Test
    public void test(){
        Map<String, Object> dataMap = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
        map.put("name", "用户1");
        map.put("sex", "男");
        map.put("age", 20);
        map.put("birth","2018-12-12");
        list.add(map);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("name", "用户2");
        map1.put("sex", "女");
        map1.put("age", 18);
        map1.put("birth","2020-12-12");
        list.add(map1);

        dataMap.put("user",list);
        DocumentGenerator generator = documentFactory.createGenerator(DocumentType.WORD);
        generator.generateFile(dataMap,"C:\\Users\\mango\\Documents\\项目资料\\demo.docx","C:\\Users\\mango\\Documents\\项目资料\\123.docx");
    }
}
