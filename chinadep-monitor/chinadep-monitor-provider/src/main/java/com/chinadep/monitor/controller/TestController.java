package com.chinadep.monitor.controller;

import com.chinadep.common.dto.ApiResponseDTO;
import com.chinadep.monitor.share.dto.test.TestDTO;
import com.chinadep.monitor.share.service.test.TestApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Title: 测试
 * </p>
 * <p>
 * Description: 测试用controller
 * </p>
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 *
 * @author Jovi
 * @version 1.0
 */
@RestController
@Api(tags = {"测试"})
public class TestController implements TestApi {
    /**
     * 测试接口
     * @param name
     * @return
     */
    @Override
    @ApiOperation(value = "测试接口",tags = "测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "姓名",required = false,dataType = "String")
    })
    public ApiResponseDTO<TestDTO> test(String name) {
        TestDTO test = new TestDTO();
        test.setAge(25).setName(name);
        test.getHobbies().add("java");
        return new ApiResponseDTO<TestDTO>().setBody(test);
    }
}
