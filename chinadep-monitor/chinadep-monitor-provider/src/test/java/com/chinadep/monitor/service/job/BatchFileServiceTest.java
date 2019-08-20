package com.chinadep.monitor.service.job;

import com.chinadep.common.constant.ServerTypeEnum;
import com.chinadep.monitor.MonitorApplication;
import com.chinadep.monitor.service.BatchFileService;
import com.chinadep.monitor.service.BatchService;
import com.chinadep.monitor.share.dto.dds.BatchProcessDTO;
import com.chinadep.monitor.share.dto.dds.BatchStartDTO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
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
public class BatchFileServiceTest {
    @Autowired
    private BatchFileService batchFileService;
    @Autowired
    private BatchService batchService;

    @Test
    public void create(){
        //批次创建
        BatchStartDTO batchStartDTO = new BatchStartDTO();
        Set<String> idTypes = Sets.newHashSet();
        idTypes.add("ID010205");
        batchStartDTO
                .setBatchStartDate(new Date())
                .setTotalNum(10L)
                .setBatchId("20190529121512")
                .setType(ServerTypeEnum.DEM)
                .setIdType(idTypes)
                .setMemId("0000180")
                .setJobId("JON20190415000001450");
        batchService.create(batchStartDTO);

        //
        Map<String,String> prepareFail = Maps.newHashMap();
        Map<String,String> sendFail = Maps.newHashMap();

        Set<String> sendSuccess = Sets.newHashSet();
        sendSuccess.add("JON20190523000001591_ID010105_20190529121512_0000.SOURCE");

        BatchProcessDTO batchProcess = new BatchProcessDTO();
        batchProcess
                .setFileEndDate(new Date())
                .setFileName("JON20190415000001450_ID010201_20190611000002_0000.TARGET")
                .setPrepareFail(prepareFail)
                .setSendFail(sendFail)
                .setSendPageNum(3L)
                .setSendSuccess(sendSuccess)
                .setBatchId("20190611000002")
                .setType(ServerTypeEnum.DEM)
                .setIdType(idTypes)
                .setMemId("0000180")
                .setJobId("JON20190415000001450");
//        batchFileService.process(batchProcess);

        //最后处理
        batchFileService.finish(batchProcess);
    }

    @Test
    public void count(){
        Map<String,String> prepareFail = Maps.newHashMap();
        Map<String,String> sendFail = Maps.newHashMap();

        Set<String> sendSuccess = Sets.newHashSet();
        sendSuccess.add("JON20190523000001591_ID010105_20190529121512_0000.SOURCE");

        Set<String> idTypes = Sets.newHashSet();
        idTypes.add("ID010205");

        BatchProcessDTO batchProcess = new BatchProcessDTO();
        batchProcess
                .setFileEndDate(new Date())
                .setFileName("JON20190523000001591_ID010105_20190529121512_0000.SOURCE")
                .setPrepareFail(prepareFail)
                .setSendFail(sendFail)
                .setSendPageNum(3L)
                .setSendSuccess(sendSuccess)
                .setBatchId("20190529121512")
                .setIdType(idTypes)
                .setMemId("0000104")
                .setJobId("JON20190523000001591");
        batchFileService.count(batchProcess);
    }

    @Test
    public void getLastBatchFile(){
        Map<String,String> prepareFail = Maps.newHashMap();
        Map<String,String> sendFail = Maps.newHashMap();

        Set<String> sendSuccess = Sets.newHashSet();
        sendSuccess.add("JON20190523000001591_ID010105_20190529121512_0000.SOURCE");

        Set<String> idTypes = Sets.newHashSet();
        idTypes.add("ID010205");

        BatchProcessDTO batchProcess = new BatchProcessDTO();
        batchProcess
                .setFileEndDate(new Date())
                .setFileName("JON20190523000001591_ID010105_20190529121512_0002.SOURCE")
                .setPrepareFail(prepareFail)
                .setSendFail(sendFail)
                .setSendPageNum(3L)
                .setSendSuccess(sendSuccess)
                .setBatchId("20190529121512")
                .setIdType(idTypes)
                .setMemId("0000104")
                .setJobId("JON20190415000001450");
        batchFileService.finish(batchProcess);


    }

    @Test
    public void finish(){
        Map<String,String> prepareFail = Maps.newHashMap();
        Map<String,String> sendFail = Maps.newHashMap();

        Set<String> sendSuccess = Sets.newHashSet();
        sendSuccess.add("JON20190415000001450_ID010201_20190611000002_0000.TARGET");

        Set<String> idTypes = Sets.newHashSet();
        idTypes.add("ID010205");

        BatchProcessDTO batchProcess = new BatchProcessDTO();
        batchProcess
                .setFileEndDate(new Date())
                .setFileName("JON20190415000001450_ID010201_20190611000002_0000.TARGET")
                .setPrepareFail(prepareFail)
                .setSendFail(sendFail)
                .setSendPageNum(3L)
                .setSendSuccess(sendSuccess)
                .setBatchId("20190611000002")
                .setType(ServerTypeEnum.DEM)
                .setIdType(idTypes)
                .setMemId("0000180")
                .setJobId("JON20190415000001450");
//        batchFileService.process(batchProcess);

        //最后处理
        batchFileService.finish(batchProcess);

    }

}
