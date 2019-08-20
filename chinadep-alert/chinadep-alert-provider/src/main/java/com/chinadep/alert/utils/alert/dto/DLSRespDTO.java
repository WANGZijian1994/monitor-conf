package com.chinadep.alert.utils.alert.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class DLSRespDTO {
    /**
     * 发送状态 成功
     */
    public static final Integer STATUS_SUCCESS = 1;
    /**
     * 发送状态 失败
     */
    public static final Integer STATUS_FAIL = 0;
    /**
     * 发送状态
     */
    private Integer status;
    /**
     * 消息体
     */
    private String msg;
    /**
     * 数据
     */
    private String data;
    /**
     * 消息等级
     */
    private Integer msgLevel;
}
