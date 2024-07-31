package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 装备管理表
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@TableName("equipment")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String pid;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称" )
    private String name;

    /**
     * 型号
     */
    @ApiModelProperty(value = "型号" )
    private String model;

    /**
     * 代别
     */
    @ApiModelProperty(value = "代别" )
    private String generation;

    /**
     * 生产厂家
     */
    @ApiModelProperty(value = "生成厂家" )
    private String manufacturer;

    /**
     * 出厂编号
     */
    @ApiModelProperty(value = "出厂编号" )
    private Integer number;

    /**
     * 出厂日期
     */
    @ApiModelProperty(value = "出厂日期" )
    private Date productionDate;

    /**
     * 部署单位
     */
    @ApiModelProperty(value = "部署单位" )
    private String deployment;

    /**
     * 启用日期
     */
    @ApiModelProperty(value = "启用日期" )
    private Date activationDate;

    /**
     * 累计工作时间
     */
    @ApiModelProperty(value = "累计工作时间" )
    private String accumulatedWorkingTime;

    /**
     * 装备封面
     */
    @ApiModelProperty(value = "装备封面" )
    private String coverUrl;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getDeployment() {
        return deployment;
    }

    public void setDeployment(String deployment) {
        this.deployment = deployment;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public String getAccumulatedWorkingTime() {
        return accumulatedWorkingTime;
    }

    public void setAccumulatedWorkingTime(String accumulatedWorkingTime) {
        this.accumulatedWorkingTime = accumulatedWorkingTime;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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
        return "Equipment{" +
        ", id=" + id +
        ", pid=" + pid +
        ", name=" + name +
        ", model=" + model +
        ", generation=" + generation +
        ", manufacturer=" + manufacturer +
        ", number=" + number +
        ", productionDate=" + productionDate +
        ", deployment=" + deployment +
        ", activationDate=" + activationDate +
        ", accumulatedWorkingTime=" + accumulatedWorkingTime +
        ", coverUrl=" + coverUrl +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        "}";
    }
}
