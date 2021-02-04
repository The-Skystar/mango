package com.tss.mangosercivea.manager.template.core;

import com.tss.mangosercivea.manager.template.DocumentModel;
import com.tss.mangosercivea.manager.template.XMLHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Map;

/**
 * Created by yangxiangjun on 2021/1/26.
 */
public class DocExecutor {
    public static final String LABEL_TEXT = "//w:t";
    private Map<String, Object> dataMap;
    private String sourceFilePath;
    private String targetFilePath;
    private DocumentModel documentModel;
    private XMLHandler header;
    private XMLHandler tail;

    public DocExecutor(Map<String, Object> dataMap, String sourceFilePath, String targetFilePath) {
        this.dataMap = dataMap;
        this.sourceFilePath = sourceFilePath;
        this.targetFilePath = targetFilePath;
    }

    public DocExecutor addChain(XMLHandler chain) {
        if (header == null) {
            this.header = this.tail = chain;
            return this;
        }
        this.tail.next(chain);
        this.tail = chain;
        return this;
    }

    public File build() {
        try {
            init();
        } catch (DocumentException e) {
            throw new RuntimeException("文档初始化失败", e);
        }
        this.header.execute(documentModel);
        File generateFile;
        try {
            generateFile = generate();
        } catch (IOException e) {
            throw new RuntimeException("文档生成失败", e);
        }
        destroy();
        return generateFile;
    }

    /**
     * 初始化document对象
     *
     * @throws DocumentException
     */
    private void init() throws DocumentException {
        if (StringUtils.isBlank(sourceFilePath)) {
            throw new RuntimeException("源模板文件为空");
        }
        convertToXml();
        SAXReader reader = new SAXReader();
        File file = new File(sourceFilePath);
        Document document = reader.read(file);
        DocumentModel documentModel = new DocumentModel();
        documentModel.setDocument(document);
        documentModel.setFilePath(sourceFilePath);
        documentModel.setNodes(document.selectNodes(LABEL_TEXT));
        this.documentModel = documentModel;
    }

    private void convertToXml() {
        File file = new File(sourceFilePath);
        if (file.isFile()) {
            String name = sourceFilePath.substring(0, sourceFilePath.lastIndexOf(".")) + ".xml";
            file.renameTo(new File(name));
            this.sourceFilePath = name;
        } else {
            throw new RuntimeException("找不到源文件");
        }
    }

    private File generate() throws IOException {
        if (StringUtils.isBlank(documentModel.getFtlPath())) {
            throw new RuntimeException("模板文件为空");
        }
        if (StringUtils.isBlank(targetFilePath)) {
            throw new RuntimeException("目标文件为空");
        }

        File file = new File(documentModel.getFtlPath());
        String directoryPath;
        String fileName;
        if (file.isFile()) {
            directoryPath = file.getParent();
            fileName = file.getName();
        } else {
            throw new RuntimeException("找不到ftl模板文件");
        }

        Configuration configuration = FreeMarkerConfig.getInstance();
        // 设置FreeMarker生成文档所需要的模板的路径
        try {
            configuration.setDirectoryForTemplateLoading(new File(directoryPath));
        } catch (IOException e) {
            throw new RuntimeException("找不到ftl模板文件所在的路径");
        }
        // 设置FreeMarker生成Word文档所需要的模板
        Template t = configuration.getTemplate(fileName);
        // 创建一个Word文档的输出流
        File targetFile = new File(targetFilePath);
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));) {
            //FreeMarker使用Word模板和数据生成Word文档
            t.process(dataMap, out);
            return file;
        } catch (TemplateException e) {
            throw new RuntimeException("文档生成失败", e);
        }
    }

    private void destroy() {
        //删除处理过程中产生的文件
//        deleteFile(DIR_PATH + sourceFileName);
        deleteFile(documentModel.getFtlPath());
        //处理生成的最终文档，上传文件服务器或构建流进行传输
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
