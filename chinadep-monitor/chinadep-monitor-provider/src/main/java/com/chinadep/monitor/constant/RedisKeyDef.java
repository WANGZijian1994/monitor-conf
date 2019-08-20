package com.chinadep.monitor.constant;

/**
 * <p>
 * Title:redis key生成器
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
public class RedisKeyDef {
    /**
     * 监控中心
     */
    private static final String MONITOR = "MONITOR";
    /**
     * redis key类型 start
     */
    public static String REDIS_KEY_START = "START";
    /**
     * redis key类型 process
     */
    public static String REDIS_KEY_PROCESS = "PROCESS";
    /**
     * 分隔符
     */
    private static final String SPLITTER = ":";
    /**
     *
     * @param jobId
     * @param batchId
     * @param type
     * @return
     */
    public static String getBatchKey(String jobId,String batchId,String type){
        StringBuilder key = new StringBuilder(MONITOR);
        key.append(SPLITTER).append(jobId)
                .append(SPLITTER).append(batchId)
                .append(SPLITTER).append(type);
        return key.toString();
    }
}
