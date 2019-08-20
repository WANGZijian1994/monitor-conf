package com.chinadep.monitor.share.dto.dds;

import com.chinadep.monitor.share.dto.dds.domain.ErrorBatch;
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
@ApiModel("其他错误信息推送")
public class ErrorBatchDTO implements ErrorBatch {
    /**
     * 会员号
     */
    @ApiModelProperty("会员号")
    private String memId;
    /**
     * 供需方类型
     */
    @ApiModelProperty("供需方类型")
    private String type;
    /**
     * 工单号
     */
    @ApiModelProperty("工单号")
    private String jobId;
    /**
     * 批次号
     */
    @ApiModelProperty("批次号")
    private String batchId;
    /**
     * 错误信息
     */
    @ApiModelProperty("错误信息")
    private String errorMessage;

}
