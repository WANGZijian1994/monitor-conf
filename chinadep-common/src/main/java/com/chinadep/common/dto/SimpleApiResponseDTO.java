package com.chinadep.common.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * Title: 基础传输对象
 * </p >
 * <p>
 * Description:
 * </p >
 * <p>
 * Copyright: Copyright (c) 2019
 * All rights reserved. 2019-04-29.
 * </p >
 *
 * @author MaoJiaWei
 * @version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("API响应")
public class SimpleApiResponseDTO extends BaseDTO {

	private static final long serialVersionUID = 1048248597035485852L;

	/**
	 * 是否成功
	 */
	@ApiModelProperty("是否成功")
	private Boolean success;

	/**
	 * 提示消息
	 */
	@ApiModelProperty("提示消息")
	private String message;

}
