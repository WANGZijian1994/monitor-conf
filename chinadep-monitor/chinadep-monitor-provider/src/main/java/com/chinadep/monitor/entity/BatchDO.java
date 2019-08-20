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
 * Title:配送表
 * </p>
 * <p>
 * Description: 记录每一次的配送情况
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
@Entity(name = "chinadep_batch")
public class BatchDO extends BaseDO {
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
     * id标识类型
     */
    @Column(name = "id_type")
    private String idType;
    /**
     * 发起方的会员号
     */
    @Column(name = "mem_id")
    private String memId;
    /**
     * 类型 dem或sup
     */
    @Column(name = "type")
    private String type;
    /**
     * 总文件个数
     */
    @Column(name = "total_num")
    private Long totalNum;
    /**
     * 发送成功的文件个数
     */
    @Column(name = "send_success_num")
    private Long sendSuccessNum;
    /**
     * 准备失败的文件个数
     * 由于文件准备错误，例如文件名或文件首行
     */
    @Column(name = "send_prepare_fail_num")
    private Long sendPrepareFailNum;
    /**
     * 发送失败的文件个数
     */
    @Column(name = "send_fail_num")
    private Long sendFailNum;
    /**
     * 是否成功
     */
    @Column(name = "is_success")
    private Boolean success;
    /**
     * 批次开始时间
     */
    @Column(name = "batch_start_time")
    private Date batchStartTime;
    /**
     * 批次结束时间
     */
    @Column(name = "batch_end_time")
    private Date batchEndTime;
    /**
     * 批次状态
     */
    @Column(name = "STATUS")
    private Integer status;
}
