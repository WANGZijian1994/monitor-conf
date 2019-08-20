package com.chinadep.alert.share.dto.alert;

import com.chinadep.alert.share.dto.alert.domain.Alert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("报警信息")
public class AlertDTO implements Alert {
    /**
     * 需要发送消息的会员编号
     */
    @ApiModelProperty("需要发送消息的会员编号")
    private String memId;
    /**
     * 需要发送的消息内容
     */
    @ApiModelProperty("需要发送的消息内容")
    private String message;
    /**
     * 发送的消息类型
     */
    @ApiModelProperty("发送的消息类型")
    private String mode;

}
