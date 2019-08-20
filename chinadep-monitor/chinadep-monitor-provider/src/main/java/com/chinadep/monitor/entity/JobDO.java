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
 * Title:工单表
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
@Entity(name = "chinadep_job")
public class JobDO extends BaseDO {
    /**
     * 分隔符
     */
    public static final String MEM_ID_SPLITTER = "|@|";
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
     * 需方会员号
     */
    @Column(name = "dem_mem_id")
    private String demMemId;
    /**
     * 供方会员号
     */
    @Column(name = "sup_mem_id")
    private String supMemId;
    /**
     * 配送方式
     */
    @Column(name = "batch")
    private Integer batch;
    /**
     * 路由方式
     */
    @Column(name = "route_method")
    private Integer routeMethod;
    /**
     * 是否xid转换
     */
    @Column(name = "id_converter")
    private Integer idConverter;
    /**
     * 是否id碰撞
     */
    @Column(name = "id_collision")
    private Integer idCollision;

    /**
     * 订单生效时间
     */
    @Column(name = "job_effect_time")
    private Date jobEffectTime;
}
