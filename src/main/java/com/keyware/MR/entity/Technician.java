package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * <p>
 * 技术人员管理表
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
@TableName("technician")
public class Technician implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private String age;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 单位
     */
    private String unit;

    /**
     * 岗位
     */
    private String post;

    @ApiModelProperty(value = "'工作年限'")
    @TableField("WORKING_YEARS")
    private String  workingYear;

    /**
     * 业务技能
     */
    private String businessSkills;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getBusinessSkills() {
        return businessSkills;
    }

    public void setBusinessSkills(String businessSkills) {
        this.businessSkills = businessSkills;
    }

    @Override
    public String toString() {
        return "Technician{" +
        ", id=" + id +
        ", name=" + name +
        ", gender=" + gender +
        ", age=" + age +
        ", phoneNumber=" + phoneNumber +
        ", unit=" + unit +
        ", post=" + post +
        ", businessSkills=" + businessSkills +
        "}";
    }
}
