package com.chinadep.monitor.share.service.dds;

import com.chinadep.common.dto.SimpleApiResponseDTO;
import com.chinadep.monitor.share.dto.dds.BatchProcessDTO;
import com.chinadep.monitor.share.dto.dds.BatchStartDTO;
import com.chinadep.monitor.share.dto.dds.ErrorBatchDTO;
import com.chinadep.monitor.share.dto.dds.JobInfoDTO;
import com.chinadep.monitor.share.dto.dds.domain.BatchProcess;
import com.chinadep.monitor.share.dto.dds.domain.BatchStart;
import com.chinadep.monitor.share.dto.dds.domain.ErrorBatch;
import com.chinadep.monitor.share.dto.dds.domain.JobInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

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
@FeignClient(name = "chinadep-monitor")
public interface DDSApi {
    /**
     * 需方订单消息发送
     * @param jobInfo
     * @return
     */
    @PostMapping(value = "/dds/job")
    SimpleApiResponseDTO job(JobInfoDTO jobInfo);

    /**
     * 供/需方供数开始消息推送
     * @param batchStart
     * @return
     */
    @PostMapping(value = "/dds/batch/start")
    SimpleApiResponseDTO batchStart(BatchStartDTO batchStart);

    /**
     * 供/需方每次发送文件后推送
     * @param batchProcess
     * @return
     */
    @PostMapping(value = "/dds/batch/process")
    SimpleApiResponseDTO batchProcess(BatchProcessDTO batchProcess);

    /**
     * 测试验证
     * @return
     */
    @PostMapping(value = "/dds/test")
    SimpleApiResponseDTO test();

    /**
     * 其他错误信息
     * @param errorBatch
     * @return
     */
    @PostMapping(value = "/dds/batch/error")
    SimpleApiResponseDTO error(ErrorBatchDTO errorBatch);
}
