package com.tss.mangoservicea.util;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
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

    /**
     * 将element嵌套进nest ， 切nest占位原来element的位置
     * @param nest
     * @param element
     */
    public static void nestSection(Element nest , Element element) {
        List<Element> children = element.getParent().elements();
        Integer index = 0;
        if (CollectionUtil.isNotEmpty(children)) {
            for (Element brotherElement : children) {
                if (element.equals(brotherElement)) {
                    break;
                }
                index++;
            }
        }
        nest.add(element.createCopy());
        children.set(index, nest);
    }

    /**
     * 获取当前节点前一个兄弟节点
     * @param element
     * @return
     */
    public static Element selectPreElement(Element element) {
        return selectDriftElement(-1, element);
    }

    /**
     * 获取当前节点后一个兄弟节点
     * @param element
     * @return
     */
    public static Element selectNextElement(Element element) {
        return selectDriftElement(1, element);
    }

    public static Element selectDriftElement(Integer index , Element element) {
        Element parent = element.getParent();
        if (parent == null) {
            return null;
        }
        List<Element> elements = parent.elements();
        Integer indexOf = 0;
        for (Element brothElement : elements) {
            if (element.equals(brothElement)) {
                break;
            }
            indexOf++;
        }
        if (indexOf >= elements.size()) {
            throw new RuntimeException("异常");
        }
        if (indexOf + index >= elements.size()) {
            throw new RuntimeException("定位element失败，数组越界");
        }
        return elements.get(indexOf+index);
    }
}
