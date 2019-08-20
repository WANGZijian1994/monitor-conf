package com.chinadep.monitor.service.test;

import com.chinadep.common.dto.ApiResponseDTO;
import com.chinadep.monitor.MonitorApplication;
import com.chinadep.monitor.share.dto.test.TestDTO;
import com.chinadep.monitor.share.service.test.TestApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Import;
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
@Import({FeignAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class})
@EnableFeignClients(clients =TestApi.class)
public class TestApiTest {
    @Autowired
    private TestApi api;
    @Test
    public void test(){
        ApiResponseDTO<TestDTO> response = api.test("jovi");
        System.out.println("==============================");
        System.out.println(response.getBody().getName());
        System.out.println("==============================");
    }
}
