package stu.cmq.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import stu.cmq.base.BaseEntity;

import java.io.Serializable;

/**
 * @author kamifeng
 * @date 23:01
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "role")
public class Role extends BaseEntity {

    @ApiModelProperty("角色Id")
    @TableId(type = IdType.AUTO)
    private Long roleId;
    @ApiModelProperty("角色名")
    private String roleName;
}
