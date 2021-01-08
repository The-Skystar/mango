package com.tss.mangosercivea.manager.template;

import cn.hutool.core.util.StrUtil;
import org.dom4j.Document;

import java.util.List;
import java.util.function.Predicate;

public abstract class XMLTemplate {

    public static final String RULE = "^[0-9a-zA-Z_.]{1,}$";
    public static final String SINGLE_HEAD = "${";
    public static final String SINGLE_TAIL = "}";
    public static final String COLL_HEAD = "$[";
    public static final String COLL_TAIL = "]";
    public static final String REGEX = ".";
    public static final String LABEL_TEXT = "//w:t";

    abstract List<TemplateParam> analysisParam(Document document);

    abstract Document normalization(Document document, List<TemplateParam> params);

    abstract Document supplement(Document document, List<TemplateParam> params);

    public String filter(String source,Predicate predicate){
        if (StrUtil.isBlank(source)) {
            throw new RuntimeException("源数据不能为空");
        }

        //将字符串转换为字符数组
        char[] chars = source.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char aChar : chars) {
            if (!predicate.test(aChar)) {
                result.append(aChar);
            }
        }
        return result.toString();
    }
}
