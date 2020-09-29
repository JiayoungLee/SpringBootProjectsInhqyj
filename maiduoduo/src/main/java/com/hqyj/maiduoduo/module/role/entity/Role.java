package com.hqyj.maiduoduo.module.user.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * author  Jayoung
 * createDate  2020/9/10 0010 14:32
 * version 1.0
 */
@Data
@Table(name = "role")
public class Role {
    private Integer roleId;
    private String roleName;
    private String desc;
}
