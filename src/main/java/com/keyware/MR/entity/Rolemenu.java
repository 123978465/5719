package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-13
 */
@TableName("rolemenu")
public class Rolemenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单id
     */
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "Rolemenu{" +
        ", roleId=" + roleId +
        ", menuId=" + menuId +
        "}";
    }
}
