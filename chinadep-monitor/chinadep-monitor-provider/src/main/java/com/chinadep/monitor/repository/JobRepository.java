package com.chinadep.monitor.repository;

import com.chinadep.common.repository.BaseRepository;
import com.chinadep.monitor.entity.JobDO;
import org.springframework.stereotype.Repository;

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
public interface JobRepository extends BaseRepository<JobDO,Long> {
    /**
     * 根据工单号查找
     * @param jobId
     * @return
     */
    JobDO findByJobId(String jobId);
}
