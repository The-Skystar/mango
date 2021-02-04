package com.tss.mangosercivea.manager.print;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiangjun on 2020/12/23.
 */
public class ExportUtil {

    /**
     * 使用FreeMarker自动生成Word文档
     * @param dataMap   生成Word文档所需要的数据
     * @param fileName  生成Word文档的全路径名称
     */
    public static void generateWord(Map<String, Object> dataMap, String fileName) throws Exception {
        // 设置FreeMarker的版本和编码格式
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");

        // 设置FreeMarker生成Word文档所需要的模板的路径
        configuration.setDirectoryForTemplateLoading(new File("C:/Users/mango/Documents/项目资料/"));
        // 设置FreeMarker生成Word文档所需要的模板
        Template t = configuration.getTemplate("demo.ftl", "UTF-8");
        // 创建一个Word文档的输出流
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8"));
        //FreeMarker使用Word模板和数据生成Word文档
        t.process(dataMap, out);
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws Exception {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX);
        String path = file.getPath();
        System.out.println(path);
        /*
         * 创建一个Map对象，将Word文档需要的数据都保存到该Map对象中
         */
        Map<String, Object> dataMap = new HashMap<>();

        /*
         * 直接在map里保存一个用户的各项信息
         * 该用户信息用于Word文档中FreeMarker普通文本处理
         * 模板文档占位符${name}中的name即指定使用这里的name属性的值"用户1"替换
         */
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
        generateWord(dataMap,"C:/Users/mango/Documents/项目资料/人员.docx");
    }
}
