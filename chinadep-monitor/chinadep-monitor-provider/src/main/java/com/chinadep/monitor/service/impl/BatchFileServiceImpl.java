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
import com.chinadep.monitor.entity.BatchFileDO;
import com.chinadep.monitor.entity.JobDO;
import com.chinadep.monitor.repository.BatchFileRepository;
import com.chinadep.monitor.repository.BatchRepository;
import com.chinadep.monitor.repository.JobRepository;
import com.chinadep.monitor.service.BatchFileService;
import com.chinadep.monitor.share.dto.dds.BatchProcessDTO;
import com.chinadep.monitor.share.dto.dds.BatchStartDTO;
import com.chinadep.monitor.share.dto.dds.domain.BatchProcess;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
public class BatchFileServiceImpl implements BatchFileService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private BatchFileRepository batchFileRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private AlertApi alertApi;

    /**
     * 传输文件中
     * @param batch
     */
    @Override
    public boolean process(BatchProcess batch) {
        String batchJson = JSON.toJSONString(batch);
        String key = RedisKeyDef.getBatchKey(batch.getJobId(),batch.getBatchId(),RedisKeyDef.REDIS_KEY_PROCESS);

        //用时间作为时间戳 以便于排序
        Date fileEndDate = batch.getFileEndDate();
        long timestamp = fileEndDate.getTime();
        this.redisTemplate.opsForZSet().add(key, batchJson,timestamp);
        //计算目前已收到的集合条数
        long count = this.redisTemplate.opsForZSet().size(key);
        log.info("key={},count=:{}",key,count);

        String batchStartKey = RedisKeyDef.getBatchKey(batch.getJobId(),batch.getBatchId(),RedisKeyDef.REDIS_KEY_START);
        String batchStartValue = redisTemplate.opsForValue().get(batchStartKey);
        if(!StringUtils.hasText(batchStartValue)){
            return false;
        }
        BatchStartDTO batchStart = JSON.parseObject(batchStartValue,BatchStartDTO.class);
        if(batchStart.getTotalNum().longValue() == count){
            return true;
        }
        return false;
    }

    /**
     * 计算redis中有多少条数据
     *
     * @param batch
     */
    @Override
    public long count(BatchProcess batch) {
        String key = RedisKeyDef.getBatchKey(batch.getJobId(),batch.getBatchId(),RedisKeyDef.REDIS_KEY_PROCESS);
        long count = this.redisTemplate.opsForZSet().size(key);
        log.info("key={},count=:{}",key,count);
        return count;
    }

    /**
     * 获取最新的数据信息
     *
     * @param batch
     * @return
     */
    @Override
    public BatchProcess getLastBatchFile(BatchProcess batch) {
        String key = RedisKeyDef.getBatchKey(batch.getJobId(),batch.getBatchId(),RedisKeyDef.REDIS_KEY_PROCESS);
        Set<String> batchSet = redisTemplate.opsForZSet().reverseRange(key,0,-1);

        //如果没有 则返回空
        if(CollectionUtils.isEmpty(batchSet)){
            return null;
        }
        //取最新的一条数据
        Iterator<String> it = batchSet.iterator();
        String value = it.next();
        BatchProcess batchProcess = JSON.parseObject(value,BatchProcess.class);
        return batchProcess;
    }

    /**
     * 获取所有的数据信息
     *
     * @param batch
     * @return
     */
    @Override
    public Map<String, BatchProcess> getBatchFile(BatchProcess batch) {
        String key = RedisKeyDef.getBatchKey(batch.getJobId(),batch.getBatchId(),RedisKeyDef.REDIS_KEY_PROCESS);
        Set<String> batchSet = redisTemplate.opsForZSet().reverseRange(key,0,-1);

        //如果没有 则返回空
        if(CollectionUtils.isEmpty(batchSet)){
            return null;
        }
        LinkedHashMap<String,BatchProcess> batchProcessMap = batchSet.stream()
                .map(e -> JSON.parseObject(e,BatchProcessDTO.class))
                .sorted(Comparator.comparing(BatchProcess::getFileEndDate).reversed())
                .collect(Collectors.toMap(
                        BatchProcess::getFileName,
                        Function.identity(),
                        (key1, key2) -> key2, LinkedHashMap::new));
        return batchProcessMap;
    }

    /**
     * 结束批次
     *
     * @param batch
     * @return
     */
    @Override
    public void finish(BatchProcess batch) {
        Map<String,BatchProcess> batchProcessMap = getBatchFile(batch);
        //如果没有 则返回空
        if(CollectionUtils.isEmpty(batchProcessMap)){
            return ;
        }
        JobDO jobDO = jobRepository.findByJobId(batch.getJobId());
        if(jobDO == null){
            return;
        }

        //取第一条 因为采用的有序map 按照时间顺序排列
        Iterator<String> it = batchProcessMap.keySet().iterator();
        BatchProcess batchProcess = batchProcessMap.get(it.next());
        List<BatchFileDO> list = Lists.newArrayList();

        //成功条数
        long successNum = 0;
        if(!CollectionUtils.isEmpty(batchProcess.getSendSuccess())){
            successNum = batchProcess.getSendSuccess().size();
        }
        //准备失败条数
        long prepareFailNum = 0;
        if(!CollectionUtils.isEmpty(batchProcess.getPrepareFail())){
            prepareFailNum = batchProcess.getPrepareFail().size();
        }
        //发送失败条数
        long failNum = 0;
        if(!CollectionUtils.isEmpty(batchProcess.getSendFail())){
            failNum = batchProcess.getSendFail().size();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("&emsp;&emsp;发送方会员号:").append(batch.getMemId()).append(",类型:").append(batch.getType()).append("。<br/>");
        sb.append("&emsp;&emsp;本次发送工单号:").append(batch.getJobId()).append(",批次号:").append(batch.getBatchId()).append("。<br/>");
        sb.append("&emsp;&emsp;本批次共").append(batch.getSendPageNum())
                .append("个文件，其中发送成功").append(successNum)
                .append("个文件，文件准备错误").append(prepareFailNum)
                .append("个，发送失败").append(failNum).append("个文件。<br/>");

        //发送成功处理
        Set<String> sendSuccess = batchProcess.getSendSuccess();
        if(!CollectionUtils.isEmpty(sendSuccess)){
            sendSuccess.remove("");
            for(String fileName:sendSuccess){
                BatchProcess fileProcess = batchProcessMap.get(fileName);

                add(list,fileName,fileProcess,true,null);
            }
        }

        //准备失败处理
        Map<String,String> prepareFail = batchProcess.getPrepareFail();
        if(!CollectionUtils.isEmpty(prepareFail)){
            sb.append("&emsp;&emsp;准备错误文件:<br/>");
            for(String fileName:prepareFail.keySet()){
                BatchProcess fileProcess = batchProcessMap.get(fileName);
                add(list,fileName,fileProcess,false,prepareFail.get(fileName));
                sb.append("&emsp;&emsp;<font color=\"#FF0000\">文件名称:</font>").append(fileName).append("<br/>");
                sb.append("&emsp;&emsp;<font color=\"#FF0000\">错误信息:</font>").append(prepareFail.get(fileName)).append("<br/>");
            }
        }

        //发送失败处理
        Map<String,String> sendFail = batchProcess.getSendFail();
        if(!CollectionUtils.isEmpty(sendFail)){
            sb.append("&emsp;&emsp;发送错误文件:<br/>");
            for(String fileName:sendFail.keySet()){
                BatchProcess fileProcess = batchProcessMap.get(fileName);
                add(list,fileName,fileProcess,false,sendFail.get(fileName));
                sb.append("&emsp;&emsp;<font color=\"#FF0000\">文件名称:</font>").append(fileName).append("<br/>");
                sb.append("&emsp;&emsp;<font color=\"#FF0000\">错误信息:</font>").append(sendFail.get(fileName)).append("<br/>");
            }
        }

        //根据 工单号 批次号 会员号 查询批次信息
        List<BatchDO> batchs = batchRepository.findByJobIdAndBatchIdAndMemId(batchProcess.getJobId(),batchProcess.getBatchId(),batchProcess.getMemId());
        BatchDO batchDO = findLastBatch(batchs);
        if(batchDO==null){
            log.info("批次信息不存在,工单号:%s,批次号:%s,会员号:%s",batchProcess.getJobId(),batchProcess.getBatchId(),batchProcess.getMemId());
            return ;
        }
        batchDO.setSendSuccessNum(successNum)
                .setSendPrepareFailNum(prepareFailNum)
                .setSendFailNum(failNum);
        boolean success = true;
        if(prepareFailNum > 0 || failNum > 0){
            success = false;
        }
        //根据类型 失败
        if(ServerTypeEnum.DEM.equals(batchDO.getType())){
            batchDO.setStatus(JobStatusEnum.DEMANDER_FAIL);
        }else if(ServerTypeEnum.SUP.equals(batchDO.getType())){
            batchDO.setStatus(JobStatusEnum.SUPPLIER_FAIL);
        }
        if(successNum == batch.getSendPageNum().longValue()){
            //根据类型 成功
            if(ServerTypeEnum.DEM.equals(batchDO.getType())){
                batchDO.setStatus(JobStatusEnum.SUPPLIER_PENDING);
            }else if(ServerTypeEnum.SUP.equals(batchDO.getType())){
                batchDO.setStatus(JobStatusEnum.FINISHED);
            }
            sb.append("&emsp;&emsp;您的本次数据传输已完成，请耐心等候对方反馈。<br/><br/>");
            //发送成功后 给对方服务发送消息
            Set<String> members = Sets.newHashSet();

            // 需方发送
            if(ServerTypeEnum.DEM.equals(batch.getType())){
                String supMemId = jobDO.getSupMemId();
                members = Sets.newHashSet(StringConcatUtils.splitter(supMemId));
            }
            // 供方发送
            if(ServerTypeEnum.SUP.equals(batch.getType())){
                String demMemId = jobDO.getDemMemId();
                members = Sets.newHashSet(StringConcatUtils.splitter(demMemId));
            }
            for(String member:members){
                StringBuilder receiveSb = new StringBuilder();
                // 发送方为需方
                if(ServerTypeEnum.DEM.equals(batch.getType())){
                    receiveSb.append("&emsp;&emsp;已收到来自需方的数据。<br/>");
                }
                // 供方发送
                if(ServerTypeEnum.SUP.equals(batch.getType())){
                    receiveSb.append("&emsp;&emsp;已收到供方返回数据。<br/>");
                }
                receiveSb.append("&emsp;&emsp;本次接收工单号:").append(batch.getJobId()).append(",批次号:").append(batch.getBatchId())
                        .append(".共").append(batch.getSendPageNum()).append("个文件。<br/>");

                AlertDTO alert = new AlertDTO();
                alert.setMemId(member)
                        .setMessage(receiveSb.toString())
                        .setMode(ModeDef.MODE_MAIL);
                alertApi.notice(alert);
            }
        }
        AlertDTO alert = new AlertDTO();
        alert.setMemId(batch.getMemId())
                .setMessage(sb.toString())
                .setMode(ModeDef.MODE_MAIL);
        alertApi.notice(alert);

        batchDO.setSuccess(success);
        batchDO.setBatchEndTime(batch.getFileEndDate());

        batchRepository.save(batchDO);
        batchFileRepository.saveAll(list);
        //删除key
        String batchProcessKey = RedisKeyDef.getBatchKey(batch.getJobId(),batch.getBatchId(),RedisKeyDef.REDIS_KEY_PROCESS);
        String batchStartKey = RedisKeyDef.getBatchKey(batch.getJobId(),batch.getBatchId(),RedisKeyDef.REDIS_KEY_START);
        redisTemplate.delete(batchStartKey);
        redisTemplate.delete(batchProcessKey);
    }

    /**
     * 将BatchProcess处理至集合中，以便于统一处理
     * @param list
     * @param fileName
     * @param fileProcess
     * @param success
     * @param errorMessage
     */
    private void add( List<BatchFileDO> list,String fileName,BatchProcess fileProcess,boolean success,String errorMessage){
        //如果fileProcess为空 一般为之前已发送过的文件 在本次不做处理
        if(fileProcess == null){
            return;
        }
        BatchFileDO batchFile = new BatchFileDO();
        batchFile.setJobId(fileProcess.getJobId())
                .setBatchId(fileProcess.getBatchId())
                .setMemId(fileProcess.getMemId())
                .setFileName(fileName)
                .setSuccess(success)
                .setSendTime(fileProcess.getFileEndDate());

        //如果不成功需要添加错误原因
        if(!success){
            batchFile.setErrorMessage(errorMessage);
        }
        list.add(batchFile);
    }

    /**
     * 获取最新的批次（默认为同一个工单、批次号、会员号）
     * @param batchs
     * @return
     */
    private BatchDO findLastBatch(List<BatchDO> batchs){
        if(CollectionUtils.isEmpty(batchs)){
            return null;
        }
        List<BatchDO> list = batchs.stream()
                .sorted(Comparator.comparing(BatchDO::getBatchStartTime).reversed())
                .collect(Collectors.toList());
        return list.get(0);
    }
}
