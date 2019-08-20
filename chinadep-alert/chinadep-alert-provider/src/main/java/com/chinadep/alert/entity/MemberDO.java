package com.chinadep.alert.entity;

import com.chinadep.common.entity.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

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
@Accessors(chain = true)
@Entity(name = "chinadep_alert_member")
public class MemberDO extends BaseDO {
    /**
     * 物理主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 会员号
     */
    @Column(name = "mem_id")
    private String memId;
    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;
    /**
     * 邮箱 以";"作为分隔符
     */
    @Column(name = "mail")
    private String mail;
}
