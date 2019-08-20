package com.chinadep.alert.test.utils;

import com.chinadep.alert.AlertApplication;
import com.chinadep.alert.utils.alert.AlertSendUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
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
public class AlertSendUtilsTest {
    /**
     * dls地址
     */
    @Value("${chinadep.dls}")
    private String dlsUrl;


    @Test
    public void send(){
        log.info(dlsUrl);
        Set<String> mails = Sets.newHashSet();
        mails.add("jovi@chinadep.com");
        AlertSendUtils.sendMail(dlsUrl,"测试邮件",mails);
    }
}
