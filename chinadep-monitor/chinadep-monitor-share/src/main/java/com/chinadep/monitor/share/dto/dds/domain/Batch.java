package com.chinadep.monitor.share.dto.dds.domain;

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
public interface Batch {
    /**
     * 工单编号
     * @return
     */
    String getJobId() ;

    /**
     * 批次号
     * @return
     */
    String getBatchId();

    /**
     * ID标识类型
     * @return
     */
    Set<String> getIdType();

    /**
     * 供需方类型
     * @return
     */
    String getType();

    /**
     * 发送方的会员号
     * @return
     */
    String getMemId() ;
}
