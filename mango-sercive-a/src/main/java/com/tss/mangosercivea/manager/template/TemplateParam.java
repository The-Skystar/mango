package com.tss.mangosercivea.manager.template;

import com.tss.mangosercivea.manager.template.enums.ParamType;

/**
 * Created by yangxiangjun on 2021/1/8.
 */
public class TemplateParam {
    private String name;
    private String ascription;
    private String path;
    private ParamType paramType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ParamType getParamType() {
        return paramType;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }

    public static TemplateParam buildParam(String text, String path){
        TemplateParam templateParam = new TemplateParam();
        templateParam.setAscription("");
        templateParam.setName(text);
        templateParam.setPath(path);
        return templateParam;
    }

    public static TemplateParam buildSingleParam(String text, String path){
        TemplateParam templateParam = buildParam(text, path);
        templateParam.setParamType(ParamType.SINGLE);
        return templateParam;
    }

    public static TemplateParam buildCollParam(String text,String path){
        TemplateParam templateParam = new TemplateParam();
        String[] split = text.split("\\.");
        if (split.length != 2) {
            throw new RuntimeException("word模板循环参数格式错误");
        }
        templateParam.setAscription(split[0]);
        templateParam.setName(split[1]);
        templateParam.setParamType(ParamType.LOOP);
        templateParam.setPath(path);
        return templateParam;
    }

    @Override
    public String toString() {
        return "TemplateParam{" +
                "name='" + name + '\'' +
                ", ascription='" + ascription + '\'' +
                ", path='" + path + '\'' +
                ", paramType=" + paramType +
                '}';
    }
}
