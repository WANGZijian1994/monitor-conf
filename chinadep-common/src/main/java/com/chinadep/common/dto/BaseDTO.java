package com.chinadep.common.dto;



import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel("基础传输对象")
public abstract class BaseDTO implements Serializable {

	private static final long serialVersionUID = 8710602112628090071L;

}
