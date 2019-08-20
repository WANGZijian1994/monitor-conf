package com.chinadep.monitor.client.alert;

import com.chinadep.alert.share.dto.alert.AlertDTO;
import com.chinadep.alert.share.service.AlertApi;
import com.chinadep.monitor.MonitorApplication;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MonitorApplication.class)
public class AlertTest {
    @Autowired
    private AlertApi alertApi;
    @Test
    public void test(){
        AlertDTO alert = new AlertDTO();
        alert.setMode("02").setMessage("测试").setMemId("0000180");
        alertApi.notice(alert);
    }
}
