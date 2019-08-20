package com.chinadep.common;

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
public class JobStatusEnum {
    /**
     * 工单取消
     */
    public static int CANCEL = 0;
    /**
     * 需方待传输
     */
    public static int DEMANDER_PENDING = 1;
    /**
     * 需方传输中
     */
    public static int DEMANDER_TRANSFERING = 2;
    /**
     * 需方传输失败
     */
    public static int DEMANDER_FAIL = 3;
    /**
     * 供方待传输
     */
    public static int SUPPLIER_PENDING = 4;
    /**
     * 供方传输中
     */
    public static int SUPPLIER_TRANSFERING = 5;
    /**
     * 供方传输失败
     */
    public static int SUPPLIER_FAIL = 6;
    /**
     * 传输完成
     */
    public static int FINISHED = 7;
}
