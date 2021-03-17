package com.tss.mangoservicea.manager.template;

/**
 * 针对xml文档的处理
 * Created by yangxiangjun on 2021/1/22.
 */
public abstract class XMLHandler {

    public static final String RULE = "^[0-9a-zA-Z_.]{1,}$";

    protected XMLHandler xmlHandler;

    public void next(XMLHandler xmlHandler) {
        this.xmlHandler = xmlHandler;
    }

    public abstract void run(DocumentModel document);

    public void execute(DocumentModel document){
        run(document);
        if (this.xmlHandler != null) {
            xmlHandler.execute(document);
        }
    }
}
