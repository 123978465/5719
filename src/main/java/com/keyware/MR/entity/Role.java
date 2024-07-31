package com.keyware.MR.entity;
import cn.hutool.db.DaoTemplate;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@TableName("role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称" )
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述" )
    private String describ;

    @TableField(exist = false)
    @ApiModelProperty(value = "菜单ids" )
    private List<String> menuIds;
    @TableField(exist = false)
    @ApiModelProperty(value = "用户ids" )
    private List<User> users;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间" )
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getDescribe() {
        return describ;
    }

    public void setDescribe(String describe) {
        this.describ = describe;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", id=" + id +
        ", roleName=" + roleName +
        "}";
    }
}
