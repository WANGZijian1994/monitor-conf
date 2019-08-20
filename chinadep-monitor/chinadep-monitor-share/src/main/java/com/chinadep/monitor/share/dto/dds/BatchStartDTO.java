package com.chinadep.monitor.share.dto.dds;

import com.chinadep.monitor.share.dto.dds.domain.BatchStart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

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
@ApiModel("批次开始消息推送")
public class BatchStartDTO extends BatchDTO implements BatchStart {

    /**
     * 批次开始时间
     */
    @ApiModelProperty("批次开始时间")
    private Date batchStartDate;

    /**
     * 总文件个数
     */
    @ApiModelProperty("总文件个数")
    private Long totalNum;

}
