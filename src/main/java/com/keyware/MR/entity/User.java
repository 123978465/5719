package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Blob;
import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-13
 */
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名" )
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码" )
    private String password;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id" )
    private String departmentId;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名" )
    private String realName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别" )
    private String gender;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色" )
    private String role;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id" )
    private String roleId;

    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门" )
    private String departmentName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号" )
    private String phoneNumber;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态" )
    private String state;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间" )
    private Date createTime;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像" )
    private String headSculpture;

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", userName=" + userName +
        ", password=" + password +
        ", departmentId=" + departmentId +
        ", realName=" + realName +
        ", gender=" + gender +
        ", role=" + role +
        ", departmentName=" + departmentName +
        ", phoneNumber=" + phoneNumber +
        ", state=" + state +
        ", createTime=" + createTime +
        "}";
    }
}
