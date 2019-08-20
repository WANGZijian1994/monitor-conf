package com.chinadep.alert.repository;

import com.chinadep.alert.entity.MemberDO;
import com.chinadep.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

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
@Repository
public interface MemberRepository extends BaseRepository<MemberDO,Long> {
    /**
     * 根据会员ID查询
     * @param memId
     * @return
     */
    MemberDO findByMemId(String memId);
}
