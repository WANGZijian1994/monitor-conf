package com.chinadep.monitor.controller;

import com.chinadep.common.dto.SimpleApiResponseDTO;
import com.chinadep.monitor.service.BatchFileService;
import com.chinadep.monitor.service.BatchService;
import com.chinadep.monitor.service.JobService;
import com.chinadep.monitor.share.dto.dds.BatchProcessDTO;
import com.chinadep.monitor.share.dto.dds.BatchStartDTO;
import com.chinadep.monitor.share.dto.dds.ErrorBatchDTO;
import com.chinadep.monitor.share.dto.dds.JobInfoDTO;
import com.chinadep.monitor.share.service.dds.DDSApi;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Title: 数据传输调用监控
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
@RestController
@Api(tags = {"DDS"})
public class DDSController implements DDSApi {
    @Autowired
    private BatchService batchService;
    @Autowired
    private BatchFileService batchFileService;
    @Autowired
    private JobService jobService;
    /**
     * 需方订单消息发送
     *
     * @param jobInfo
     * @return
     */
    @Override
    public SimpleApiResponseDTO job(@RequestBody JobInfoDTO jobInfo) {
        jobService.create(jobInfo);
        return new SimpleApiResponseDTO()
                .setSuccess(true)
                .setMessage("订单接收成功");
    }

    /**
     * 供/需方供数开始消息推送
     *
     * @param batchStart
     * @return
     */
    @Override
    public SimpleApiResponseDTO batchStart(@RequestBody BatchStartDTO batchStart) {
        batchService.create(batchStart);
        return new SimpleApiResponseDTO()
                .setSuccess(true)
                .setMessage("批次接收成功");
    }

    /**
     * 供/需方每次发送文件后推送
     *
     * @param batchProcess
     * @return
     */
    @Override
    public SimpleApiResponseDTO batchProcess(@RequestBody BatchProcessDTO batchProcess) {
        boolean isAll = batchFileService.process(batchProcess);
        //如果总数量等于redis中的数量 则说明完全发送完成
        if(isAll){
            batchFileService.finish(batchProcess);
        }
        //统计所有文件信息
        return new SimpleApiResponseDTO()
                .setSuccess(true)
                .setMessage("批次文件接收成功");
    }


    /**
     * 测试验证
     *
     * @return
     */
    @Override
    public SimpleApiResponseDTO test() {
        return new SimpleApiResponseDTO()
                .setSuccess(true)
                .setMessage("验证成功");
    }

    /**
     * 其他错误信息
     *
     * @param errorBatch
     * @return
     */
    @Override
    public SimpleApiResponseDTO error(@RequestBody ErrorBatchDTO errorBatch) {
        log.info("发送方会员号:{},发送方类型:{},错误信息:{},工单号:{},批次号:{}",
                errorBatch.getMemId(),errorBatch.getType(),errorBatch.getErrorMessage(),
                errorBatch.getJobId(),errorBatch.getBatchId());
        batchService.error(errorBatch);
        return new SimpleApiResponseDTO()
                .setSuccess(true)
                .setMessage("错误消息发送成功");
    }
}
