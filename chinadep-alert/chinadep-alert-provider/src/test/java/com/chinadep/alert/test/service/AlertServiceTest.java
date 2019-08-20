package com.chinadep.alert.test.service;

import com.chinadep.alert.AlertApplication;
import com.chinadep.alert.service.AlertService;
import com.chinadep.alert.share.dto.alert.AlertDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class AlertServiceTest {
    @Autowired
    private AlertService alertService;

    @Test
    public void test(){
        AlertDTO alert = new AlertDTO();
        alert.setMemId("0000180").setMessage("测试").setMode("01");
        alertService.notice(alert);
    }
}
