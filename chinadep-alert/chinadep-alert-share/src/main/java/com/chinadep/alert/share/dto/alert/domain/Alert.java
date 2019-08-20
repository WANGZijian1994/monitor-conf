package com.chinadep.alert.share.dto.alert.domain;

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
public interface Alert {
    /**
     * 发送对象的会员编号
     * @return
     */
    String getMemId();

    /**
     * 消息内容
     * @return
     */
    String getMessage();

    /**
     * 消息模式
     * @return
     */
    String getMode();
}
