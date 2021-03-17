package com.tss.mangoservicea.manager.template.handler;

import com.tss.mangoservicea.manager.template.DocumentModel;
import com.tss.mangoservicea.manager.template.enums.ParamCategory;
import com.tss.mangoservicea.manager.template.TemplateParam;
import com.tss.mangoservicea.manager.template.XMLHandler;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 解析xml文档中的参数值
 * <p>
 * Created by yangxiangjun on 2021/1/22.
 */
public class AnalysisParamHandler extends XMLHandler {

    @Override
    public void run(DocumentModel document) {
        List<TemplateParam> params = new ArrayList<>();
        List<Element> nodes = document.getNodes();
        //获取所有的文本信息节点
        Stack stack = new Stack<>();
        boolean isAnalysis = false;
        boolean isStack = false;
        for (Element node : nodes) {
            String path = node.getUniquePath();
            //获取节点的文本信息
            String text = node.getText();
            //如果文本信息包含占位符，则该文本信息后面讲出现与之对应的参数
            for (ParamCategory value : ParamCategory.values()) {
                isAnalysis = isAnalysis || text.contains(value.getParamCate()[0]) || isStack;
            }

            // 如果flag变量为ture，代表当前正在寻找被xml格式转换打乱的参数，将接下来的文本信息入栈，
            // 并且判断是否找到了占位符结束标志，如果找到，代表找到参数，isAnalysis设置为false
            // 此时开启对文本信息的逐个字符读取
            if (isAnalysis) {

                ParamCategory paramCate = null;
                for (int i = 0; i < text.length(); i++) {
                    if (!isStack) {
                        char[] chars = new char[2];
                        chars[0] = text.charAt(i);
                        chars[1] = text.charAt(i + 1);
                        String str = new String(chars);
                        //判断字符串在遍历中是否找到了占位符的开始标志
                        for (ParamCategory value : ParamCategory.values()) {
                            if (str.equals(value.getParamCate()[0])) {
                                if (!stack.isEmpty()) {
                                    throw new RuntimeException("word模板参数解析失败");
                                }
                                //标注当前找到的占位符
                                paramCate = value;
                                isStack = true;
                            }
                        }
                    }

                    //如果找到了占位符开始标志，后续的字符需要入栈
                    if (isStack) {
                        stack.push(text.charAt(i));
                    }

                    //已经遍历到当前占位符的尾部，表示占位符的所有字符都已入栈，需要从栈中获取参数
                    if (paramCate.getParamCate()[1].charAt(0) == text.charAt(i)) {
                        isStack = false;
                        params.add(getParam(stack, paramCate, path));
                    }
                }
            }
        }
        document.setParams(params);
    }

    private TemplateParam getParam(Stack stack, ParamCategory paramCategory, String path) {
        int size = stack.size();
        StringBuilder tempParam = new StringBuilder();
        for (int i = 0; i < size; i++) {
            tempParam.append(stack.pop());
        }
        tempParam = tempParam.reverse();
        int startIndex = tempParam.indexOf(paramCategory.getParamCate()[0]) + 2;
        int endIndex = tempParam.indexOf(paramCategory.getParamCate()[1]);
        String param = tempParam.substring(startIndex, endIndex);
        boolean matches = Pattern.matches(RULE, param);
        if (!matches) {
            throw new RuntimeException("word模板参数格式错误");
        }
        TemplateParam templateParam = new TemplateParam();
        switch (paramCategory.getParamType()) {
            case LOOP:
                templateParam = TemplateParam.buildCollParam(param, path);
                break;
            case IMAGE:
                break;
            case SINGLE:
                templateParam = TemplateParam.buildParam(param, path);
                break;
            case CHECKBOX:
                break;
            default:
                templateParam = null;
                break;
        }
        return templateParam;
    }
}
