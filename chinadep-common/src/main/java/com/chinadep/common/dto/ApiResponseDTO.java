package com.chinadep.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * BASE RESPONSE DTO
 * 
 * @author chunliang.you
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel("API响应")
@Accessors(chain = true)
public class ApiResponseDTO<T> extends SimpleApiResponseDTO {

	private static final long serialVersionUID = -7252418279732964895L;

	/**
	 * 响应内容
	 */
	@ApiModelProperty("响应时间")
	private T body;

}
