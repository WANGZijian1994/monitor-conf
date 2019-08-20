package com.chinadep.alert.utils.alert;

import com.alibaba.fastjson.JSON;
import com.chinadep.alert.share.constant.ModeDef;
import com.chinadep.alert.utils.alert.dto.DLSReqDTO;
import com.chinadep.alert.utils.alert.dto.DLSRespDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Title: 消息警告发送
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
public class AlertSendUtils {
    /**
     * 成功
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * 发送邮件
     * @param dlsUrl
     * @param message
     * @param mails
     * @return
     */
    public static String sendMail(String dlsUrl,String message, Set<String> mails){
        log.info(dlsUrl);
        DLSReqDTO reqDTO = new DLSReqDTO();
        reqDTO.setMode(ModeDef.MODE_MAIL)
                .setContent(message)
                .setNoticeTo(mails);
        MediaType media = MediaType.parse("application/json; charset=utf-8");
        String jsonStr = JSON.toJSONString(reqDTO);

        OkHttpClient client = new OkHttpClient.Builder()
                // 设置连接超时时间
                .connectTimeout(20, TimeUnit.SECONDS)
                // 设置读取超时时间
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(dlsUrl)

                .post(RequestBody.create(media, jsonStr))
                .build();
        Response response = null;
        // 重试10次
        try {
            response = client.newCall(request).execute();
            String respStr = response.body().string();
            log.info("dls调用通知服务返回结果:{}",respStr);
            DLSRespDTO respDTO = JSON.parseObject(respStr,DLSRespDTO.class);
            if(respDTO.getStatus().intValue() == DLSRespDTO.STATUS_SUCCESS.intValue()){
                return SUCCESS;
            }
            return respDTO.getMsg();
        }catch (IOException e){
            log.error(e.getMessage());
            return e.getMessage();
        }

    }
}
