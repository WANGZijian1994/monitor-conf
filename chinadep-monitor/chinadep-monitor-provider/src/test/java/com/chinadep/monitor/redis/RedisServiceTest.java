package com.chinadep.monitor.redis;

import com.chinadep.monitor.MonitorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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
public class RedisServiceTest {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void send(){
        redisTemplate.opsForList().rightPush("names", "jovi");

    }
}
