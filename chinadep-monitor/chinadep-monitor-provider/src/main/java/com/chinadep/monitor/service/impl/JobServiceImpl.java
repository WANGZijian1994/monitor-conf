package com.chinadep.monitor.service.impl;

import com.chinadep.infra.utils.string.StringConcatUtils;
import com.chinadep.monitor.service.JobService;
import com.chinadep.monitor.share.dto.dds.JobInfoDTO;
import com.chinadep.monitor.share.dto.dds.domain.JobInfo;
import com.chinadep.monitor.entity.JobDO;
import com.chinadep.monitor.repository.JobRepository;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository repository;

    /**
     * 创建工单信息
     * @param jobInfo
     */
    @Override
    public void create(JobInfo jobInfo) {
        JobDO job = repository.findByJobId(jobInfo.getJobId());
        if(job == null){
            job = new JobDO();
        }

        //将需方集合通过|@|进行分割
        jobInfo.getDemMemIds().remove("");
        String demMemId = StringConcatUtils.joiner(jobInfo.getDemMemIds());
        //将供方集合通过|@|进行分割
        jobInfo.getSupMemIds().remove("");
        String supMemId = StringConcatUtils.joiner(jobInfo.getSupMemIds());
        job.setJobId(jobInfo.getJobId())
                .setDemMemId(demMemId)
                .setSupMemId(supMemId)
                .setBatch(jobInfo.getBatch())
                .setRouteMethod(jobInfo.getRouteMethod())
                .setIdCollision(jobInfo.getIdCollision())
                .setIdConverter(jobInfo.getIdConverter())
                .setJobEffectTime(jobInfo.getJobEffectTime());
        repository.save(job);
    }

    /**
     * 根据工单号查找
     *
     * @param jobId
     */
    @Override
    public JobInfo find(String jobId) {
        JobDO jobDO = repository.findByJobId(jobId);
        //将需方集合通过|@|进行分割
        Set<String> demMemId = Sets.newHashSet(StringConcatUtils.splitter(jobDO.getDemMemId()));
        //将供方集合通过|@|进行分割
        Set<String> supMemId = Sets.newHashSet(StringConcatUtils.splitter(jobDO.getSupMemId()));
        JobInfoDTO job = new JobInfoDTO();
        job.setDemMemIds(demMemId)
                .setSupMemIds(supMemId)
                .setBatch(jobDO.getBatch())
                .setRouteMethod(jobDO.getRouteMethod())
                .setIdCollision(jobDO.getIdCollision())
                .setIdConverter(jobDO.getIdConverter())
                .setJobEffectTime(jobDO.getJobEffectTime())
                .setJobId(jobDO.getJobId());
        return job;
    }
}
