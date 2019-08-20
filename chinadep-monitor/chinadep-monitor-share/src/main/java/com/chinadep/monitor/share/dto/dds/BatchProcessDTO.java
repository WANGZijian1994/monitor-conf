package com.chinadep.monitor.share.dto.dds;

import com.chinadep.monitor.share.dto.dds.domain.BatchProcess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Map;
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
@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("批次发送文件后推送")
public class BatchProcessDTO extends BatchDTO implements BatchProcess {
    /**
     * 本次发送的文件名
     */
    @ApiModelProperty("本次发送的文件名")
    private String fileName;

    /**
     * 需要发送的文件总数
     */
    @ApiModelProperty("需要发送的文件总数")
    private Long sendPageNum;

    /**
     * 发送成功的文件
     */
    @ApiModelProperty("发送成功的文件")
    private Set<String> sendSuccess;

    /**
     * 发送失败的文件
     */
    @ApiModelProperty("发送失败的文件")
    private Map<String,String> sendFail;

    /**
     * 准备失败的文件
     */
    @ApiModelProperty("准备失败的文件")
    private Map<String,String> prepareFail;

    /**
     * 文件处理的结束时间
     */
    @ApiModelProperty("文件处理的结束时间")
    private Date fileEndDate;


}
