package com.chinadep.monitor.service;

import com.chinadep.monitor.share.dto.dds.domain.BatchStart;
import com.chinadep.monitor.share.dto.dds.domain.ErrorBatch;

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
public interface BatchService {
    /**
     * 创建批次
     * @param batch
     */
    void create(BatchStart batch);

    /**
     * 错误批次
     * @param errorBatch
     */
    void error(ErrorBatch errorBatch);
}
