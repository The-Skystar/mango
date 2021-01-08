package com.tss.mangosercivea.util;

import com.tss.mangosercivea.manager.template.TemplateParam;
import com.tss.mangosercivea.manager.template.WordXMLTemplate;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.List;

/**
 * word的xml格式文件解析
 * Created by yangxiangjun on 2021/1/5.
 */
@Slf4j
public class XMLUtil {

    /**
     * 根据路径获取到xml文件的Document对象
     * @param path
     * @return
     * @throws DocumentException
     */
    public static Document parse(String path) throws DocumentException {
        SAXReader reader = new SAXReader();
        File file = new File(path);
        Document document = reader.read(file);
        return document;
    }

    /**
     * 根据url获取xml文件的Document对象
     * @param url
     * @return
     * @throws DocumentException
     */
    public static Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }

    /**
     * 写入xml文件
     * @param document
     * @param filename
     * @return
     */
    public static boolean write(Document document,String filename){
        boolean flag = true;
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");    // 指定XML编码
            XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                    new FileOutputStream(filename)),format);
            writer.write(document);
            writer.close();
        } catch (Exception ex) {
            flag = false;
            log.error("写入xml文件出错",ex);
        }
        return flag;
    }

    public static void main(String[] args) throws DocumentException {
        WordXMLTemplate wordXMLTemplate = new WordXMLTemplate();
        Document document = parse("C:/Users/mango/Documents/项目资料/投资决策审批表.xml");
        List<TemplateParam> strings = wordXMLTemplate.analysisParam(document);
//        strings.forEach(System.out::println);
        Document normalization = wordXMLTemplate.normalization(document, strings);
        wordXMLTemplate.supplement(normalization,strings);
//        String text = "砂浆${gsuagu}绝对是卡";
//        int startIndex = (text.indexOf("${") == -1 ? 0 : text.indexOf("{")) + (text.indexOf("$[") == -1 ? 0 : text.indexOf("[")) + 1;
//        int endIndex = (text.indexOf("}") == -1 ? 0 : text.indexOf("}")) + (text.indexOf("]") == -1 ? 0 : text.indexOf("]"));
//        text = text.substring(startIndex,endIndex);
//        System.out.println(text);
    }
}
