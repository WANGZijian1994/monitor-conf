package com.chinadep.monitor.share.dto.dds;

import com.chinadep.monitor.share.dto.dds.domain.JobInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 * Title:订单消息请求
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
@ApiModel("订单信息")
public class JobInfoDTO extends JobDTO implements JobInfo {
    /**
     * 需方成员ID
     */
    @ApiModelProperty("需方成员ID")
    private Set<String> demMemIds;
    /**
     * 供方成员ID
     */
    @ApiModelProperty("供方成员ID")
    private Set<String> supMemIds;
    /**
     * 配送方式
     */
    @ApiModelProperty("配送方式")
    private Integer batch;
    /**
     * 路由方式
     */
    @ApiModelProperty("路由方式")
    private Integer routeMethod;
    /**
     * 是否xid转换
     */
    @ApiModelProperty("是否xid转换")
    private Integer idConverter;
    /**
     * 是否id碰撞
     */
    @ApiModelProperty("是否id碰撞")
    private Integer idCollision;
    /**
     * 订单开始时间
     */
    @ApiModelProperty("订单生效时间")
    private Date jobEffectTime;


}
