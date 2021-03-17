package com.tss.mangoservicea.manager.template.handler;

import com.tss.mangoservicea.manager.template.core.ConvertXmlWriter;
import com.tss.mangoservicea.manager.template.DocumentModel;
import com.tss.mangoservicea.manager.template.XMLHandler;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 将处理后的document对象重新写入文件
 * Created by yangxiangjun on 2021/1/27.
 */
public class XMLWriterHandler extends XMLHandler {

    private static Logger log = LoggerFactory.getLogger(XMLWriterHandler.class);

    @Override
    public void run(DocumentModel document) {
        OutputStreamWriter outputStreamWriter = null;
        XMLWriter writer = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(document.getFilePath()));
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");    // 指定XML编码
            writer = new ConvertXmlWriter(outputStreamWriter,format);
            writer.write(document.getDocument());
        } catch (FileNotFoundException e) {
            log.error("找不到指定文件",e);
        } catch (IOException e) {
            log.error("重新写xml文件失败",e);
        } finally {
            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (writer!= null) {
                    writer.close();
                }
            } catch (IOException e) {

            }
        }
    }
}
