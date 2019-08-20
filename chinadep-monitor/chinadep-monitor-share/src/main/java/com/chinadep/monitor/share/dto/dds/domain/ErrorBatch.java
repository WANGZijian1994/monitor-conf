package com.chinadep.monitor.share.dto.dds.domain;

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
public interface ErrorBatch {
    /**
     * 会员号
     * @return
     */
    String getMemId();

    /**
     * 会员类型
     * @return
     */
    String getType();

    /**
     * 工单号
     * @return
     */
    String getJobId();

    /**
     * 批次号
     * @return
     */
    String getBatchId();

    /**
     * 错误信息
     * @return
     */
    String getErrorMessage();
}
