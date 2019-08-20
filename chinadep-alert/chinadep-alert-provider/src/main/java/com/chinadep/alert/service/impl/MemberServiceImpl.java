package com.chinadep.alert.service.impl;

import com.chinadep.alert.entity.MemberDO;
import com.chinadep.alert.repository.MemberRepository;
import com.chinadep.alert.service.MemberService;
import com.chinadep.alert.share.dto.alert.domain.Member;
import com.chinadep.infra.utils.string.StringConcatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository repository;

    /**
     * 创建会员
     *
     * @param member
     */
    @Override
    public void create(Member member) {
        MemberDO memberDO = new MemberDO();
        memberDO.setName(member.getName())
                .setMemId(member.getMemId());
        //处理邮箱
        if(!CollectionUtils.isEmpty(member.getMails())){
            String mail = StringConcatUtils.joiner(member.getMails(), StringConcatUtils.SEMICOLON_SPLITTER);
            memberDO.setMail(mail);
        }
        repository.save(memberDO);
    }
}
