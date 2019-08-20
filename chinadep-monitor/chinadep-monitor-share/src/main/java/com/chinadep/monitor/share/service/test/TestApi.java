package com.chinadep.monitor.share.service.test;

import com.chinadep.common.dto.ApiResponseDTO;
import com.chinadep.monitor.share.dto.test.TestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@FeignClient(name = "chinadep-monitor")
public interface TestApi {
    /**
     * 测试接口,用name获取
     * @param name
     * @return
     */
    @GetMapping(value = "/test/hello")
    ApiResponseDTO<TestDTO> test(@RequestParam("name") String name);
}
