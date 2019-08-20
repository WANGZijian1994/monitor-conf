package com.chinadep.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinadep.alert.share.constant.ModeDef;
import com.chinadep.alert.share.dto.alert.AlertDTO;
import com.chinadep.alert.share.service.AlertApi;
import com.chinadep.common.JobStatusEnum;
import com.chinadep.common.constant.ServerTypeEnum;
import com.chinadep.infra.utils.string.StringConcatUtils;
import com.chinadep.monitor.constant.RedisKeyDef;
import com.chinadep.monitor.entity.BatchDO;
import com.chinadep.monitor.repository.BatchRepository;
import com.chinadep.monitor.service.BatchService;
import com.chinadep.monitor.share.dto.dds.domain.BatchStart;
import com.chinadep.monitor.share.dto.dds.domain.ErrorBatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private BatchRepository repository;
    @Autowired
    private AlertApi alertApi;

    /**
     * 创建批次
     *
     * @param batch
     */
    @Override
    public void create(BatchStart batch) {

        //现在redis中备份保留一份
        String jsonStr = JSON.toJSONString(batch);
        String key = RedisKeyDef.getBatchKey(batch.getJobId(),batch.getBatchId(),RedisKeyDef.REDIS_KEY_START);
        redisTemplate.opsForValue().set(key,jsonStr);

        BatchDO batchDO = new BatchDO();
        String idType = StringConcatUtils.joiner(batch.getIdType());
        if(ServerTypeEnum.DEM.equals(batchDO.getType())){
            batchDO.setStatus(JobStatusEnum.DEMANDER_TRANSFERING);
        }else if(ServerTypeEnum.SUP.equals(batchDO.getType())){
            batchDO.setStatus(JobStatusEnum.SUPPLIER_TRANSFERING);
        }
        batchDO.setJobId(batch.getJobId())
                .setBatchId(batch.getBatchId())
                .setMemId(batch.getMemId())
                .setIdType(idType)
                .setType(batch.getType())
                .setTotalNum(batch.getTotalNum())
                .setBatchStartTime(batch.getBatchStartDate());
        repository.save(batchDO);
    }

    /**
     * 错误批次
     *
     * @param errorBatch
     */
    @Override
    public void error(ErrorBatch errorBatch) {
        AlertDTO alert = new AlertDTO();
        alert.setMode(ModeDef.MODE_MAIL).setMemId(errorBatch.getMemId());
        StringBuilder sb = new StringBuilder();
        sb.append("&emsp;&emsp;发送方会员号:").append(errorBatch.getMemId()).append(",类型:").append(errorBatch.getType()).append("。<br/>");
        if(StringUtils.hasText(errorBatch.getJobId())){
            sb.append("&emsp;&emsp;工单号:").append(errorBatch.getJobId()).append("。");
        }
        if(StringUtils.hasText(errorBatch.getBatchId())){
            sb.append("&emsp;&emsp;批次号:").append(errorBatch.getBatchId()).append("。");
        }
        sb.append("&emsp;&emsp;错误信息:").append(errorBatch.getErrorMessage()).append("。");
        alert.setMessage(sb.toString());
        alertApi.notice(alert);
    }
}
