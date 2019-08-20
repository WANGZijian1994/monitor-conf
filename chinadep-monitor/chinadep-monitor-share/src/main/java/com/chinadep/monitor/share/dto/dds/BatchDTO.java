package com.chinadep.monitor.share.dto.dds;

import com.chinadep.monitor.share.dto.dds.domain.Batch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel("批次信息")
public class BatchDTO extends JobDTO implements Batch {
    /**
     * 批次号
     */
    @ApiModelProperty("批次号")
    private String batchId;
    /**
     * ID标识类型
     */
    @ApiModelProperty("批次号")
    private Set<String> idType;
    /**
     * 发送方的会员编号
     */
    @ApiModelProperty("发送方的会员编号")
    private String memId;
    /**
     * 类型 供需方 dem sup
     */
    @ApiModelProperty("供需方类型 dem sup")
    private String type;
}
