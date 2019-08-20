package com.chinadep.monitor.share.dto.dds.domain;

import java.util.Date;
import java.util.Map;
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
public interface BatchProcess extends Batch{
    /**
     * 文件名称
     * @return
     */
    String getFileName();

    /**
     * 总文件个数
     * @return
     */
    Long getSendPageNum();

    /**
     * 发送成功文件
     * @return
     */
    Set<String> getSendSuccess();

    /**
     * 发送失败文件
     * @return
     */
    Map<String, String> getSendFail();

    /**
     * 准备失败文件
     * @return
     */
    Map<String, String> getPrepareFail();

    /**
     * 文件发送结束时间
     * @return
     */
    Date getFileEndDate();
}
