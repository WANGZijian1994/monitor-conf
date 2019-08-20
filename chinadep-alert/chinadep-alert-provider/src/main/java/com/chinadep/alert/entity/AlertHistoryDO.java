package com.chinadep.alert.entity;

import com.chinadep.common.entity.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

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
@Accessors(chain = true)
@Entity(name = "chinadep_alert_history")
public class AlertHistoryDO extends BaseDO {
    /**
     * 物理主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 会员号
     */
    @Column(name = "mem_id")
    private String memId;
    /**
     * 发送模式
     */
    @Column(name = "mode")
    private String mode;
    /**
     * 邮箱
     */
    @Column(name = "mail")
    private String mail;
    /**
     * 手机号，用于短信
     */
    @Column(name = "phone")
    private String phone;
    /**
     * 消息内容
     */
    @Column(name = "message")
    private String message;
    /**
     * 是否成功
     */
    @Column(name = "success")
    private Boolean success;
    /**
     * 错误消息
     */
    @Column(name = "error_message")
    private String errorMessage;
}
