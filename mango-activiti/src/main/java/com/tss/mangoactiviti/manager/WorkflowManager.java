package com.tss.mangoactiviti.manager;


import com.tss.mangoactiviti.model.ProcessDef;

import java.util.List;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/11 10:03
 * @since JDK 1.8
 */
public interface WorkflowManager {
    String createDeployByBPMN(String filePath,String name);

    List<ProcessDef> queryProcessDefinitions();

    void deleteDeployment(String id);
}
