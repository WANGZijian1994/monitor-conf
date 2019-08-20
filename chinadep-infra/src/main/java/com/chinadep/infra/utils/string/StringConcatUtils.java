package com.chinadep.infra.utils.string;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.Collection;

/**
 * <p>
 * Title:字符串拼接工具类
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
public class StringConcatUtils {
    /**
     * 分隔符（默认）
     */
    public static final String DEFAULT_SPLITTER = "|@|";
    /**
     * 分隔符(下划线)
     */
    public static final String UNDERLINE_SPLITTER = "_";
    /**
     * 分隔符(分号)
     */
    public static final String SEMICOLON_SPLITTER = ";";

    /**
     * 字符串拼接
     * @return
     */
    public static String joiner(Collection<String> collection,String splitter){
        Joiner joiner = Joiner.on(splitter).skipNulls();
        return joiner.join(collection);
    }

    /**
     * 字符串拼接
     * @return
     */
    public static String joiner(Collection<String> collection){
        return joiner(collection,DEFAULT_SPLITTER);
    }

    /**
     * 字符串分割
     * @return
     */
    public static Collection<String> splitter(String str,String splitter){
        Splitter sp = Splitter.on(splitter).omitEmptyStrings().trimResults();
        return sp.splitToList(str);
    }

    /**
     * 字符串分割
     * @return
     */
    public static Collection<String> splitter(String str){
        return splitter(str,DEFAULT_SPLITTER);
    }

}
