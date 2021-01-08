package com.tss.mangosercivea.manager.template;

/**
 * Created by yangxiangjun on 2021/1/8.
 */
public class TemplateParam {
    String name;
    String ascription;
    boolean loop;

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

    public boolean getLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public static TemplateParam buildSingleParam(String text){
        TemplateParam templateParam = new TemplateParam();
        templateParam.setAscription("");
        templateParam.setName(text);
        templateParam.setLoop(false);
        return templateParam;
    }

    public static TemplateParam buildCollParam(String text){
        TemplateParam templateParam = new TemplateParam();
        String[] split = text.split("\\.");
        if (split.length != 2) {
            throw new RuntimeException("word模板循环参数格式错误");
        }
        templateParam.setAscription(split[0]);
        templateParam.setName(split[1]);
        templateParam.setLoop(true);
        return templateParam;
    }

    @Override
    public String toString() {
        return "TemplateParam{" +
                "name='" + name + '\'' +
                ", ascription='" + ascription + '\'' +
                ", loop=" + loop +
                '}';
    }
}
