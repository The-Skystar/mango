package com.tss.mangoservicea.manager.template.handler;

import com.tss.mangoservicea.manager.template.*;
import com.tss.mangoservicea.manager.template.enums.ParamCategory;
import com.tss.mangoservicea.manager.template.enums.ParamType;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Created by yangxiangjun on 2021/1/25.
 */
public class SupplementHandler extends XMLHandler {
    @Override
    public void run(DocumentModel document) {
        List<TemplateParam> params = document.getParams();
        //首先对参数进行筛选和分类，同一个集合的参数放到同一个列表里，剔除单参数
        Map<String, List<TemplateParam>> collParams = params.stream()
                .filter(param -> ParamType.LOOP.equals(param.getParamType()))
                .collect(Collectors.groupingBy(TemplateParam::getAscription));
        for (String asc : collParams.keySet()) {
            List<TemplateParam> templateParams = collParams.get(asc);
            AtomicReference<Element> ancestorTr = new AtomicReference<>();
            templateParams.forEach(templateParam -> {
                //根据参数所在标签的路径获取标签
                List<Element> nodes = document.getDocument().selectNodes(templateParam.getPath());
                nodes.get(0).setText(ParamCategory.SINGLE.getParamCate()[0] + templateParam.getAscription() + "." + templateParam.getName() + ParamCategory.SINGLE.getParamCate()[1]);
                //获取当前w:t的w:tr父节点
                List<Element> ancestorTrList = nodes.get(0).selectNodes("ancestor::w:tr[1]");
                if (!ancestorTrList.isEmpty()) {
                    Element element = ancestorTrList.get(0);
                    if (null != ancestorTr.get() && !element.equals(ancestorTr.get())) {
                        throw new RuntimeException("word文档关于循环数据的配置不符合规定");
                    }
                    ancestorTr.set(element);
                } else {
                    throw new RuntimeException("处理集合数据时，模板的配置只能借助表格");
                }
            });

            Element parent = ancestorTr.get().getParent();
            List<Element> elements = parent.elements();
            //获取w:tr标签的位置下标，用于添加循环标签后对其进行重写
            int index = elements.indexOf(ancestorTr.get());
            //创建一个#list标签，并设置循环数据的属性
            Element foreach = DocumentHelper.createElement("#list");
            foreach.addAttribute("name", asc +" as "+asc);
            Element copy = ancestorTr.get().createCopy();
            foreach.add(copy);

            //添加if标签用于对循环数据的空判断
            Element iflist = DocumentHelper.createElement("#if");
            iflist.addAttribute("name",String.format("%s ??&& (%s?size>0)",asc,asc));
            iflist.add(foreach);
            elements.set(index,iflist);
        }
    }
}
