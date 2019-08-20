package com.chinadep.alert.service;

import com.chinadep.alert.share.dto.alert.domain.Member;

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
public interface MemberService {
    /**
     * 创建会员
     * @param member
     */
    void create(Member member);
}
