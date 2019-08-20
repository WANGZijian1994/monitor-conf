package com.chinadep.monitor.entity;

import com.chinadep.common.entity.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * Title: 传输文件表
 * </p>
 * <p>
 * Description: 记录每个文件的传输情况
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
@Entity(name = "chinadep_batch_file")
public class BatchFileDO extends BaseDO {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 工单号
     */
    @Column(name = "job_id")
    private String jobId;
    /**
     * 批次号
     */
    @Column(name = "batch_id")
    private String batchId;
    /**
     * 发起方的会员号
     */
    @Column(name = "mem_id")
    private String memId;
    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;
    /**
     * 是否成功
     */
    @Column(name = "is_success")
    private Boolean success;
    /**
     * 错误原因
     */
    @Column(name = "error_message")
    private String errorMessage;
    /**
     * 发送时间
     */
    @Column(name = "send_time")
    private Date sendTime;
}
