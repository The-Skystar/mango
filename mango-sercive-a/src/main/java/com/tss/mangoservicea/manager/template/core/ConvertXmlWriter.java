package com.tss.mangoservicea.manager.template.core;


import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 重写writer方法，对xml文件中新添加的特殊字符进行转义
 *
 * Created by yangxiangjun on 2021/1/27.
 */
public class ConvertXmlWriter extends XMLWriter {

    public ConvertXmlWriter(OutputStreamWriter out, OutputFormat format) throws UnsupportedEncodingException {
        super(out, format);
    }

    @Override
    protected void writeEscapeAttributeEntities(String txt) throws IOException {
        if (txt != null) {
            this.writer.write(txt);
        }
    }
}
