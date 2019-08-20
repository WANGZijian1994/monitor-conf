package com.chinadep.monitor.share.dto.dds.domain;

import java.util.Date;

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
public interface BatchStart extends Batch{

    /**
     * 批次开始时间
     * @return
     */
    Date getBatchStartDate();

    /**
     * 文件总个数
     * @return
     */
    Long getTotalNum();
}
