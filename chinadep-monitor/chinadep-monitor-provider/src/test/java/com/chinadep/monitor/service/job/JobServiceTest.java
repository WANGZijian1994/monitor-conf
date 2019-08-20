package com.chinadep.monitor.service.job;

import com.chinadep.monitor.MonitorApplication;
import com.chinadep.monitor.service.JobService;
import com.chinadep.monitor.share.dto.dds.JobInfoDTO;
import com.chinadep.monitor.share.dto.dds.domain.JobInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 *
 * @author Jovi
 * @version 1.0
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MonitorApplication.class})
public class JobServiceTest {
    @Autowired
    private JobService service;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    /**
     * 工单创建
     */
    @Test
    public void create(){
        JobInfoDTO jobInfo = new JobInfoDTO();
        Set<String> demMemIds = Sets.newHashSet();
        demMemIds.add("0000181");
        Set<String> supMemIds = Sets.newHashSet();
        supMemIds.add("0000180");
        jobInfo.setDemMemIds(demMemIds)
                .setSupMemIds(supMemIds)
                .setBatch(1)
                .setRouteMethod(0)
                .setIdCollision(0)
                .setIdConverter(0)
                .setJobEffectTime(new Date())
                .setJobId("JON20190415000001450");
        service.create(jobInfo);
    }

    /**
     * 根据工单号查询
     */
    @Test
    public void find(){
        JobInfo info = service.find("JON20190415000001450");
        System.out.println(info);
    }
}
