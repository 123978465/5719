package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 设备管理表
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@TableName("device")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号" )
    private String number;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称" )
    private String name;

    /**
     * 设备标识
     */
    @ApiModelProperty(value = "设备标识" )
    private String mark;

    /**
     * 采购日期
     */
    @ApiModelProperty(value = "采购日期" )
    private Date purchaseDate;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者" )
    private String createUser;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Device{" +
        ", id=" + id +
        ", number=" + number +
        ", name=" + name +
        ", mark=" + mark +
        ", purchaseDate=" + purchaseDate +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        "}";
    }
}
