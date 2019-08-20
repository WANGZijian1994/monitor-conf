package com.chinadep.alert.test.service;

import com.chinadep.alert.AlertApplication;
import com.chinadep.alert.service.MemberService;
import com.chinadep.alert.share.dto.alert.MemberDTO;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlertApplication.class)
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void create(){
        MemberDTO member = new MemberDTO();
        member.setMemId("0000180").setName("测试");
        Set<String> mails = Sets.newHashSet();
        mails.add("jovi@chinadep.com");
        member.setMails(mails);
        memberService.create(member);
    }
}
