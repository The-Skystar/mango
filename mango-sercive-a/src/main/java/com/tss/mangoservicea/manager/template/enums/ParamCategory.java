package com.tss.mangoservicea.manager.template.enums;

public enum ParamCategory {
    SINGLE(ParamType.SINGLE, new String[]{"${", "}"}),
    LOOP(ParamType.LOOP, new String[]{"$[", "]"}),
    IMAGE(ParamType.IMAGE, new String[]{"@{", "}"}),
    CHECKBOX(ParamType.CHECKBOX, new String[]{"#{", "}"}),
    ;

    private ParamType paramType;
    private String[] paramCate;

    ParamCategory(ParamType paramType, String[] paramCate) {
        this.paramType = paramType;
        this.paramCate = paramCate;
    }

    public ParamType getParamType() {
        return paramType;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }

    public String[] getParamCate() {
        return paramCate;
    }

    public void setParamCate(String[] paramCate) {
        this.paramCate = paramCate;
    }
}
