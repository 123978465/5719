package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 组织管理
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@TableName("department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称" )
    private String name;

    /**
     * 部门负责人
     */
    @ApiModelProperty(value = "部门负责人" )
    private String head;

    /**
     * 部门负责人联系电话
     */
    @ApiModelProperty(value = "部门负责人联系电话" )
    private String phoneNumber;

    /**
     * 部门人数
     */
    @ApiModelProperty(value = "部门人数" )
    private Integer peopleNumber;

    /**
     * 部门描述
     */
    @ApiModelProperty(value = "部门描述" )
    private String describ;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期" )
    private Date createTime;

    /**
     * 父部门id
     */
    @ApiModelProperty(value = "父部门id" )
    private String pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public String getDescribe() {
        return describ;
    }

    public void setDescribe(String describe) {
        this.describ = describe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Department{" +
        ", id=" + id +
        ", name=" + name +
        ", head=" + head +
        ", phoneNumber=" + phoneNumber +
        ", peopleNumber=" + peopleNumber +
        ", describe=" + describ +
        ", createTime=" + createTime +
        ", pid=" + pid +
        "}";
    }
}
