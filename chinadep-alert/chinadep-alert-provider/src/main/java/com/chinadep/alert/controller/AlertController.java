package com.chinadep.alert.controller;

import com.chinadep.alert.service.AlertService;
import com.chinadep.alert.share.dto.alert.AlertDTO;
import com.chinadep.alert.share.service.AlertApi;
import com.chinadep.common.dto.SimpleApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/alert/")
public class AlertController implements AlertApi {
    @Autowired
    private AlertService alertService;
    /**
     * @param alert
     */
    @Override
    @PostMapping(value = "notice")
    public SimpleApiResponseDTO notice(@RequestBody AlertDTO alert) {
        return alertService.notice(alert);
    }
}
