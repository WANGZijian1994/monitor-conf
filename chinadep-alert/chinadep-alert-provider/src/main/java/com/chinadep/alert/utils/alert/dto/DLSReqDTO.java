package com.chinadep.alert.utils.alert.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class DLSReqDTO {
    /**
     * 调用模式
     */
    private String mode;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 通知对象
     */
    private Set<String> noticeTo;
}
