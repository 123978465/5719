package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.io.Serializable;
/**
 * <p>
 * 故障原因表
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-15
 */
@TableName("faultreason")
public class Faultreason implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 故障名称
     */
    private String name;

    /**
     * 所属装备
     */
    private String affiliation;

    /**
     * 故障部位
     */
    private String position;

    /**
     * 故障现象
     */
    private String phenomenon;

    /**
     * 故障等级
     */
    private String level;

    /**
     * 故障影响
     */
    private String effect;

    /**
     * 故障分析
     */
    private String analysis;

    /**
     * 故障检测方法
     */
    private String method;

    /**
     * 故障现象id
     */
    private String pid;

    @ApiModelProperty(value = "操作者")
    @TableField("TECHNICAL_PERSONNEL")
    private String  rechnicalPersonnel;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Faultreason{" +
        ", id=" + id +
        ", name=" + name +
        ", affiliation=" + affiliation +
        ", position=" + position +
        ", phenomenon=" + phenomenon +
        ", level=" + level +
        ", effect=" + effect +
        ", analysis=" + analysis +
        ", method=" + method +
        ", pid=" + pid +
        ", createTime=" + createTime +
        "}";
    }
}
