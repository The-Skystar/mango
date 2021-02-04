package com.tss.mangosercivea.manager.template.handler;

import com.tss.mangosercivea.manager.template.DocumentModel;
import com.tss.mangosercivea.manager.template.XMLHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author 张新华
 * @version V1.0
 * @Package com.github.zxhtom.export.chain.impl
 * @date 2020年05月11日, 0011 10:37
 * @Copyright © 2020 zxhtom有限公司
 */
@Log4j2
public class XmlToFtlHandler extends XMLHandler {

    @Override
    public void run(DocumentModel document) {
        this.replacePartTextContent(document);
    }
    
    /**
    * @author zxhtom
    * @Description 目前需要修改的集合属性，因为xml不支持ftl的list标签。这里需要我们修改
    * @Date 10:41 2020年05月11日, 0011
    * @Param
    * @return void
    */
    public synchronized void replacePartTextContent(DocumentModel documentModel) {
        String filePath = documentModel.getFilePath();
        // 读
        try {
            File file = new File(filePath);
            File targetFile = new File(filePath.substring(0,filePath.lastIndexOf("."))+".ftl");
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            // 内存流, 作为临时流
            CharArrayWriter tempStream = new CharArrayWriter();
            // 替换
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                // 替换每行中, 符合条件的字符串
                line = replacePart(line);
                // 将该行写入内存
                tempStream.write(line);
                // 添加换行符
                //tempStream.append(System.getProperty("line.separator"));
            }
            // 关闭 输入流
            bufIn.close();
            // 将内存中的流 写入 文件
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(targetFile), "UTF-8"));
            tempStream.writeTo(out);
            out.close();
            documentModel.setFtlPath(targetFile.getPath());
        } catch (Exception e) {
            log.error(e);
        }

    }

    public String replacePart(String line) {
        StringBuffer sb = new StringBuffer();
        Pattern p = compile("(\\#(\\w+) name=\"([ \\>\\?\\&\\!\\'\\$\\(\\)\\{\\}\\,\\.\\=\\f\\n\\r\\t\\vA-Za-z0-9_]+)\")");
        Matcher m = p.matcher(line) ;
        while( m.find() ){
            String labelName = m.group(2);
            String group = m.group(3);
            String v = "#"+labelName+" "+group;
            //注意，在替换字符串中使用反斜线 (\) 和美元符号 ($) 可能导致与作为字面值替换字符串时所产生的结果不同。
            //美元符号可视为到如上所述已捕获子序列的引用，反斜线可用于转义替换字符串中的字面值字符。
            v = v.replace("\\", "\\\\").replace("$", "\\$");
            //替换掉查找到的字符串
            m.appendReplacement(sb, v) ;
        }
        //别忘了加上最后一点
        m.appendTail(sb) ;
        return sb.toString();
    }

}
