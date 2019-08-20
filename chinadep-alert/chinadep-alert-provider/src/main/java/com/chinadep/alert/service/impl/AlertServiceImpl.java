package com.chinadep.alert.service.impl;

import com.chinadep.alert.entity.AlertHistoryDO;
import com.chinadep.alert.entity.MemberDO;
import com.chinadep.alert.repository.AlertHistoryRepository;
import com.chinadep.alert.repository.MemberRepository;
import com.chinadep.alert.service.AlertService;
import com.chinadep.alert.share.constant.ModeDef;
import com.chinadep.alert.share.dto.alert.AlertDTO;
import com.chinadep.alert.share.dto.alert.domain.Alert;
import com.chinadep.alert.utils.alert.AlertSendUtils;
import com.chinadep.common.dto.SimpleApiResponseDTO;
import com.chinadep.infra.utils.string.StringConcatUtils;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
@Service
public class AlertServiceImpl implements AlertService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AlertHistoryRepository alertHistoryRepository;
    /**
     * dls地址
     */
    @Value("${chinadep.dls}")
    private String dlsUrl;

    /**
     * 消息通知
     *
     * @param alert
     * @return
     */
    @Override
    public SimpleApiResponseDTO notice(AlertDTO alert) {

        //根据会员ID查询 获取该会员号下的邮箱
        MemberDO memberDO = memberRepository.findByMemId(alert.getMemId());
        if (memberDO == null) {
            AlertHistoryDO alertHistory = new AlertHistoryDO();
            String errorMessage = "会员号不存在";
            alertHistory.setSuccess(false)
                    .setMemId(alert.getMemId())
                    .setMessage(alert.getMessage())
                    .setMode(alert.getMode())
                    .setErrorMessage(errorMessage);
            alertHistoryRepository.save(alertHistory);
            return new SimpleApiResponseDTO().setSuccess(false).setMessage(errorMessage);
        }
        SimpleApiResponseDTO dto = new SimpleApiResponseDTO();
        switch (alert.getMode()){
            //邮箱
            case ModeDef.MODE_MAIL:
                dto = sendMail(alert,memberDO);
                break;
            //其他
            default:
                String errorMessage = "模式不支持";
                AlertHistoryDO alertHistory = new AlertHistoryDO();
                alertHistory.setSuccess(false)
                        .setMemId(alert.getMemId())
                        .setMessage(alert.getMessage())
                        .setMode(alert.getMode())
                        .setErrorMessage(errorMessage);
                alertHistoryRepository.save(alertHistory);
                dto.setSuccess(false).setMessage(errorMessage);
        }
        return dto;

    }

    private SimpleApiResponseDTO sendMail(Alert alert, MemberDO member){
        AlertHistoryDO alertHistory = new AlertHistoryDO();
        alertHistory.setMemId(alert.getMemId());
        if (!StringUtils.hasText(member.getMail())) {
            String errorMessage = "会员邮箱不存在";
            alertHistory.setSuccess(false)
                    .setMail(member.getMail())
                    .setMessage(alert.getMessage())
                    .setMode(alert.getMode())
                    .setErrorMessage(errorMessage);
            alertHistoryRepository.save(alertHistory);
            return new SimpleApiResponseDTO().setSuccess(false).setMessage(errorMessage);
        }
        Set<String> mails = Sets.newHashSet(StringConcatUtils.splitter(member.getMail(), StringConcatUtils.SEMICOLON_SPLITTER));
        String msg = AlertSendUtils.sendMail(dlsUrl, alert.getMessage(), mails);
        if (!AlertSendUtils.SUCCESS.equals(msg)){
            alertHistory.setSuccess(false)
                    .setMail(member.getMail())
                    .setMessage(alert.getMessage())
                    .setMode(alert.getMode())
                    .setErrorMessage(msg);
            alertHistoryRepository.save(alertHistory);
            return new SimpleApiResponseDTO().setSuccess(false).setMessage(msg);
        }
        alertHistory.setSuccess(true)
                .setMail(member.getMail())
                .setMessage(alert.getMessage())
                .setMode(alert.getMode());
        alertHistoryRepository.save(alertHistory);
        return new SimpleApiResponseDTO().setSuccess(true).setMessage(msg);
    }
}
