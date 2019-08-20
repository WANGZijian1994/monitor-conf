package com.chinadep.monitor.service;

import com.chinadep.monitor.share.dto.dds.domain.JobInfo;

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
public interface JobService {
    /**
     * 创建工单信息
     * @param jobInfo
     */
    void create(JobInfo jobInfo);

    /**
     * 根据工单号查找
     * @param jobId
     * @return
     */
    JobInfo find(String jobId);
}
