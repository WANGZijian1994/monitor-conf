package com.chinadep.alert.service;

import com.chinadep.alert.share.dto.alert.AlertDTO;
import com.chinadep.common.dto.SimpleApiResponseDTO;

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
public interface AlertService {
    /**
     * 消息通知
     * @param alert
     * @return
     */
    SimpleApiResponseDTO notice(AlertDTO alert);
}
