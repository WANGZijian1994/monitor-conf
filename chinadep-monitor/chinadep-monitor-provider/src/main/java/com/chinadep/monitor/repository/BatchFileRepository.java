package com.chinadep.monitor.repository;

import com.chinadep.common.repository.BaseRepository;
import com.chinadep.monitor.entity.BatchFileDO;
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
public interface BatchFileRepository extends BaseRepository<BatchFileDO,Long> {
}
