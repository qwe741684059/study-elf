package stu.cmq.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import stu.cmq.base.BaseEntity;

/**
 * @author kamifeng
 * @date 15:00
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "user_role")
public class UserRole extends BaseEntity {
    @ApiModelProperty("user_role_id")
    @TableId(type = IdType.AUTO)
    private Long userRoleId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("角色id")
    private Long roleId;
}
