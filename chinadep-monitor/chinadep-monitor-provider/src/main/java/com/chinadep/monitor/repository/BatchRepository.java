package com.chinadep.monitor.repository;

import com.chinadep.common.repository.BaseRepository;
import com.chinadep.monitor.entity.BatchDO;
import org.springframework.stereotype.Repository;

import java.util.List;

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
@Repository
public interface BatchRepository extends BaseRepository<BatchDO,Long> {
    /**
     * 查询工单信息
     * @param jobId   工单号
     * @param batchId 批次号
     * @param memId   会员号
     * @return
     */
    List<BatchDO> findByJobIdAndBatchIdAndMemId(String jobId, String batchId, String memId);
}
