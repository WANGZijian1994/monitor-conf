package com.chinadep.alert.share.dto.alert.domain;

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
public interface Member {
    /**
     * 物理主键
     * @return
     */
    Long getId();

    /**
     * 会员ID
     * @return
     */
    String getMemId();

    /**
     * 姓名
     * @return
     */
    String getName();

    /**
     * 会员编号
     * @return
     */
    Set<String> getMails();
}
