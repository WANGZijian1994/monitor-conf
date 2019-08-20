package com.chinadep.monitor.service.job;

import com.chinadep.monitor.MonitorApplication;
import com.chinadep.monitor.service.BatchService;
import com.chinadep.monitor.share.dto.dds.BatchStartDTO;
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
public class BatchServiceTest {
    @Autowired
    private BatchService service;
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
     * 创建批次
     */
    @Test
    public void create(){
        BatchStartDTO batchStartDTO = new BatchStartDTO();
        Set<String> idTypes = Sets.newHashSet();
        idTypes.add("ID010205");
        batchStartDTO
                .setBatchStartDate(new Date())
                .setTotalNum(10L)
                .setBatchId("20190529121512")
                .setIdType(idTypes)
                .setMemId("0000104")
                .setJobId("JON20190415000001450");
        service.create(batchStartDTO);
    }

}
