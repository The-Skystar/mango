package com.tss.mangosercivea.controller;

import com.tss.mangosercivea.service.Job;
import com.tss.mangosercivea.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @PostMapping("/addjob")
    public void addJob(){
        long start = System.currentTimeMillis();
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",1);
        quartzService.deleteJob("job", "test");
        quartzService.addJob(Job.class, "job", "test", "0 * * * * ?", map);
        map.put("name",2);
        quartzService.deleteJob("job2", "test");
        quartzService.addJob(Job.class, "job2", "test", "10 * * * * ?", map);
        map.put("name",3);
        quartzService.deleteJob("job3", "test2");
        quartzService.addJob(Job.class, "job3", "test2", "15 * * * * ?", map);
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
    }
}
