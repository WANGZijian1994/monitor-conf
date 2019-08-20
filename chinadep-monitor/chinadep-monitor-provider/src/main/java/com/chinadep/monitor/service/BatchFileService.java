package com.chinadep.monitor.service;

import com.chinadep.monitor.share.dto.dds.domain.BatchProcess;

import java.util.Map;

/**
 * <p>
 * Title: 批次文件业务逻辑
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
public interface BatchFileService {
    /**
     * 传输文件中
     * @param batch
     * @return 是否已经接受完成
     */
    boolean process(BatchProcess batch);

    /**
     * 计算redis中有多少条数据
     * @param batch
     * @return
     */
    long count(BatchProcess batch);

    /**
     * 获取最新的数据信息
     * @param batch
     * @return
     */
    BatchProcess getLastBatchFile(BatchProcess batch);

    /**
     * 获取所有的数据信息
     * @param batch
     * @return
     */
    Map<String,BatchProcess> getBatchFile(BatchProcess batch);

    /**
     * 结束批次
     * @param batch
     * @return
     */
    void finish(BatchProcess batch);
}
