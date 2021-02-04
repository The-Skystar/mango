package com.tss.mangosercivea.manager.template.handler;

import com.tss.mangosercivea.manager.template.*;
import com.tss.mangosercivea.manager.template.enums.ParamCategory;
import com.tss.mangosercivea.manager.template.enums.ParamType;
import org.dom4j.Element;

import java.util.List;

/**
 * 对参数进行占位符的检测，如果占位符和参数被分开，不在同一个标签下，进行校正
 *
 * Created by yangxiangjun on 2021/1/22.
 */
public class NormalizationHandler extends XMLHandler {

    @Override
    public void run(DocumentModel document) {
        List<Element> nodes = document.getNodes();
        List<TemplateParam> params = document.getParams();
        //获取所有的文本信息节点
        for (Element node : nodes) {
            //获取节点的文本信息
            String text = node.getText();
            //如果文本信息包好占位符，但是占位符不全，则首先将占位符去除
            for (ParamCategory value : ParamCategory.values()) {
                if (text.contains(value.getParamCate()[0]) ^ text.contains(value.getParamCate()[1])) {
                    text.replace(value.getParamCate()[0],"");
                    text.replace(value.getParamCate()[1],"");
                    node.setText(text);
                }
            }

            //如果文本信息中包含参数，判断参数是否有正确的占位符，没有正确的占位符，则添加正确的占位符
            String finalText = text;
            params.forEach((templateParam) -> {
                String param = templateParam.getParamType().equals(ParamType.LOOP) ? templateParam.getAscription() + "." + templateParam.getName() : templateParam.getName();
                String relParam = "";
                String paramCategory[] = new String[2];
                switch (templateParam.getParamType()){
                    case LOOP:
                        paramCategory = ParamCategory.LOOP.getParamCate();
                        break;
                    case IMAGE:
                        paramCategory = ParamCategory.IMAGE.getParamCate();
                        break;
                    case SINGLE:
                        paramCategory = ParamCategory.SINGLE.getParamCate();
                        break;
                    case CHECKBOX:
                        paramCategory = ParamCategory.CHECKBOX.getParamCate();
                        break;
                    default:
                        break;
                }
                relParam = paramCategory.length != 2 ? param : paramCategory[0] + param + paramCategory[1];
                node.setText(finalText.replace(param,relParam));
            });
        }
    }

}
