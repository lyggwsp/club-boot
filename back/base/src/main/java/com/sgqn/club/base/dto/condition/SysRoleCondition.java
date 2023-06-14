package com.sgqn.club.base.dto.condition;

import com.sgqn.club.base.bean.PageCondition;
import com.sgqn.club.base.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wspstart
 * @description 角色的查询条件
 * @create 2023-06-13 22:37
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysRoleCondition extends PageCondition<SysRole> {
}
