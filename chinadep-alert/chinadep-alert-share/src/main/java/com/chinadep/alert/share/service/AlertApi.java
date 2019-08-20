package com.chinadep.alert.share.service;

import com.chinadep.alert.share.dto.alert.AlertDTO;
import com.chinadep.common.dto.SimpleApiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

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
@FeignClient(name = "chinadep-alert")
public interface AlertApi {
    /**
     * 消息通知
     * @param alert
     * @return
     */
    @PostMapping(value = "/alert/notice")
    SimpleApiResponseDTO notice(AlertDTO alert);
}
