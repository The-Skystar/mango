package com.tss.mangosercivea.manager.template;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by yangxiangjun on 2021/1/26.
 */
public class DocumentModel {
    private Document document;
    private String filePath;
    private String ftlPath;
    protected List<TemplateParam> params;
    protected List<Element> nodes;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFtlPath() {
        return ftlPath;
    }

    public void setFtlPath(String ftlPath) {
        this.ftlPath = ftlPath;
    }

    public List<TemplateParam> getParams() {
        return params;
    }

    public void setParams(List<TemplateParam> params) {
        this.params = params;
    }

    public List<Element> getNodes() {
        return nodes;
    }

    public void setNodes(List<Element> nodes) {
        this.nodes = nodes;
    }

}
