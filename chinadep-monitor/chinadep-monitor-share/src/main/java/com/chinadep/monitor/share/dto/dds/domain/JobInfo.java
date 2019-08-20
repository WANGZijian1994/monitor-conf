package com.chinadep.monitor.share.dto.dds.domain;

import java.util.Date;
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
public interface JobInfo extends Job {
    /**
     * 需方会员号
     * @return
     */
    Set<String> getDemMemIds() ;

    /**
     * 供方会员号
     * @return
     */
    Set<String> getSupMemIds() ;

    /**
     * 配送方式
     * @return
     */
    Integer getBatch();

    /**
     * 路由方式
     * @return
     */
    Integer getRouteMethod();

    /**
     * 是否xid转换
     * @return
     */
    Integer getIdConverter();

    /**
     * 是否id碰撞
     * @return
     */
    Integer getIdCollision();

    /**
     * 订单生效时间
     * @return
     */
    Date getJobEffectTime();
}
