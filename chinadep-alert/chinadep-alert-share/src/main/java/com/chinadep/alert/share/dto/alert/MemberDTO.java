package com.chinadep.alert.share.dto.alert;

import com.chinadep.alert.share.dto.alert.domain.Member;
import io.swagger.annotations.ApiModel;
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
@ApiModel("会员信息")
public class MemberDTO implements Member {
    /**
     * 物理主键
     */
    private Long id;
    /**
     * 会员ID
     */
    private String memId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 需要发送的邮箱
     */
    private Set<String> mails;



}
