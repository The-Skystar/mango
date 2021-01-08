package com.tss.mangosercivea.manager.template;

import org.dom4j.Document;
import org.dom4j.Node;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * word的xml文件处理
 * Created by yangxiangjun on 2021/1/6.
 */
public class WordXMLTemplate extends XMLTemplate{
    @Override
    public List<TemplateParam> analysisParam(Document document) {
        List<TemplateParam> params = new ArrayList<>();
        //获取所有的文本信息节点
        List<Node> nodes = document.selectNodes(LABEL_TEXT);
        Stack stack = new Stack<>();
        boolean flag = false;
        for (Node node : nodes) {
            //获取节点的文本信息
            String text = node.getText();
            //如果文本信息包含完整单数据占位符，则该文本信息为单数据参数
//            if (text.contains(SINGLE_HEAD) && text.contains(SINGLE_TAIL)) {
//                int startIndex = text.indexOf(SINGLE_HEAD) + 2;
//                int endIndex = text.indexOf(SINGLE_TAIL);
//                text = text.substring(startIndex,endIndex);
//                boolean matches = Pattern.matches(RULE, text);
//                if (!matches) {
//                    throw new RuntimeException("word模板参数格式错误");
//                }
//                TemplateParam templateParam = TemplateParam.buildSingleParam(text);
//                params.add(templateParam);
//                continue;
//            }
//
//            //如果文本信息包含完整循环数据占位符，则该文本信息为循环数据参数
//            if (text.contains(COLL_HEAD) && text.contains(COLL_TAIL)) {
//                int startIndex = text.indexOf(COLL_HEAD) + 2;
//                int endIndex = text.indexOf(COLL_TAIL);
//                text = text.substring(startIndex,endIndex);
//                boolean matches = Pattern.matches(RULE, text);
//                if (!matches) {
//                    throw new RuntimeException("word模板参数格式错误");
//                }
//                TemplateParam templateParam = TemplateParam.buildCollParam(text);
//                params.add(templateParam);
//                continue;
//            }

            //如果文本信息包含不完整占位符，则该文本信息后面讲出现与之对应的参数
            if (text.contains(SINGLE_HEAD) || text.contains(COLL_HEAD)) {
                if (!stack.isEmpty()) {
                    throw new RuntimeException("word模板参数解析失败");
                }
                flag = true;
            }

            // 如果flag变量为ture，代表当前正在寻找被xml格式转换打乱的参数，将接下来的文本信息入栈，
            // 并且判断是否找到了占位符结束标志，如果找到，代表找到参数，flag设置为false
            if (flag) {
                for (int i =0; i <text.length(); i++){
                    stack.push(text.charAt(i));
                }
                //找到占位符结束标志，当前参数已被找到，处理stack栈信息，获取参数
                if (text.contains(SINGLE_TAIL)) {
                    flag = false;
                    int size = stack.size();
                    StringBuilder tempParam = new StringBuilder();
                    for (int i = 0; i < size; i++) {
                        tempParam.append(stack.pop());
                    }
                    tempParam = tempParam.reverse();
                    int startIndex = tempParam.indexOf(SINGLE_HEAD) + 2;
                    int endIndex = tempParam.indexOf(SINGLE_TAIL);
                    String param = tempParam.substring(startIndex, endIndex);
                    boolean matches = Pattern.matches(RULE, param);
                    if (!matches) {
                        throw new RuntimeException("word模板参数格式错误");
                    }
                    TemplateParam templateParam = TemplateParam.buildSingleParam(param);
                    params.add(templateParam);
                }

                if (text.contains(COLL_TAIL)) {
                    flag = false;
                    int size = stack.size();
                    StringBuilder tempParam = new StringBuilder();
                    for (int i = 0; i < size; i++) {
                        tempParam.append(stack.pop());
                    }
                    tempParam = tempParam.reverse();
                    int startIndex = tempParam.indexOf(COLL_HEAD) + 2;
                    int endIndex = tempParam.indexOf(COLL_TAIL);
                    String param = tempParam.substring(startIndex, endIndex);
                    boolean matches = Pattern.matches(RULE, param);
                    if (!matches) {
                        throw new RuntimeException("word模板参数格式错误");
                    }
                    TemplateParam templateParam = TemplateParam.buildCollParam(param);
                    params.add(templateParam);
                }
            }
        }
        return params;
    }

    @Override
    public Document normalization(Document document,List<TemplateParam> params) {
        //获取所有的文本信息节点
        List<Node> nodes = document.selectNodes(LABEL_TEXT);
        for (Node node : nodes) {
            //获取节点的文本信息
            String text = node.getText();
            //如果文本信息包好占位符，但是占位符不全，则需要调整
            if ((text.contains(SINGLE_HEAD) ^ text.contains(SINGLE_TAIL)) || (text.contains(COLL_HEAD) ^ text.contains(COLL_TAIL))) {
                text = filter(text,(c) -> "${}[]".contains(String.valueOf(c)));
                node.setText(text);
            }

            //如果文本信息中包含参数，判断参数是否有正确的占位符
            String finalText = text;
            params.forEach((templateParam) ->{
                String param = templateParam.getName();
                String relSingleParam = SINGLE_HEAD + param + SINGLE_TAIL;
                String relCollParam = COLL_HEAD + param + COLL_TAIL;
                if (finalText.equals("START_DATE")) {
                    if (finalText.contains(param) && !finalText.contains(relSingleParam) && !finalText.contains(relCollParam)){
                        String replace = templateParam.getLoop() ? finalText.replace(param, relCollParam) : finalText.replace(param, relSingleParam);
                        node.setText(replace);
                    }
                }
            });
        }

        return document;
    }

    @Override
    public Document supplement(Document document,List<TemplateParam> params) {
        //首先对参数进行筛选和分类，同一个集合的参数放到同一个列表里，剔除单参数
        Map<String, List<TemplateParam>> collParams = params.stream()
                .filter(param -> param.getLoop())
                .collect(Collectors.groupingBy(TemplateParam::getAscription));
        for (String asc : collParams.keySet()) {
            System.out.println(collParams.get(asc));
            System.out.println();
        }
        return null;
    }
}
