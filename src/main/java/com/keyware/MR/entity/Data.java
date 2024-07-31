package com.keyware.MR.entity;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author Yaojz
 * @since 2023-04-06
 */
@TableName("data_copy1")
public class Data implements Serializable {

//    private static final long serialVersionUID = 1L;


    @TableField(exist = false)
//    @JSONField(serialize = false)
    @JsonIgnore
    private Integer count = 12;
    @ApiModelProperty(value = "id" )
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    @ApiModelProperty(value = "油门杆角度")
    @TableField("AP")
    private String ap;

    @ApiModelProperty(value = "工步参数最小值")
    @TableField("N1")
    private String N1;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("N2")
    private String N2;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("P300a")
    private String P300a;

//    @ApiModelProperty(value = "工步参数最小值")
//    @TableField("Т4")
//    private String Т4;

    @ApiModelProperty(value = "低压压气机导向器安装角度")
    @TableField("a1")
    private String a1;
    @ApiModelProperty(value = "高压压气机导向器安装角度")
    @TableField("a2")
    private String a2;

    @ApiModelProperty(value = "工步参数最小值")
    @TableField("GTHz")
    private String GTHz;

    @ApiModelProperty(value = "高压压气机导向器角度")
    @TableField("a2n")
    private String a2n;
    @ApiModelProperty(value = "低压压气机导向器角度")
    @TableField("a1n")
    private String a1n;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("N1n")
    private String N1n;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("PK")
    private String PK;


    @ApiModelProperty(value = "工步参数最小值")
    @TableField("TFOUR")
    private String TFOUR;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("N2n")
    private String N2n;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("bianma")
    private String bianma;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getN1() {
        return N1;
    }

    public void setN1(String n1) {
        N1 = n1;
    }

    public String getN2() {
        return N2;
    }

    public void setN2(String n2) {
        N2 = n2;
    }

    public String getP300a() {
        return P300a;
    }

    public void setP300a(String p300a) {
        P300a = p300a;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getGTHz() {
        return GTHz;
    }

    public void setGTHz(String GTHz) {
        this.GTHz = GTHz;
    }

    public String getA2n() {
        return a2n;
    }

    public void setA2n(String a2n) {
        this.a2n = a2n;
    }

    public String getA1n() {
        return a1n;
    }

    public void setA1n(String a1n) {
        this.a1n = a1n;
    }

    public String getN1n() {
        return N1n;
    }

    public void setN1n(String n1n) {
        N1n = n1n;
    }

    public String getPK() {
        return PK;
    }

    public void setPK(String PK) {
        this.PK = PK;
    }

    public String getTFOUR() {
        return TFOUR;
    }

    public void setTFOUR(String TFOUR) {
        this.TFOUR = TFOUR;
    }

    public String getN2n() {
        return N2n;
    }

    public void setN2n(String n2n) {
        N2n = n2n;
    }

    public String getBianma() {
        return bianma;
    }

    public void setBianma(String bianma) {
        this.bianma = bianma;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Data{" +
                "count='" + count + '\'' +
                "id='" + id + '\'' +
                ", ap='" + ap + '\'' +
                ", N1='" + N1 + '\'' +
                ", N2='" + N2 + '\'' +
                ", P300a='" + P300a + '\'' +
                ", a1='" + a1 + '\'' +
                ", a2='" + a2 + '\'' +
                ", GTHz='" + GTHz + '\'' +
                ", a2n='" + a2n + '\'' +
                ", a1n='" + a1n + '\'' +
                ", N1n='" + N1n + '\'' +
                ", PK='" + PK + '\'' +
                ", TFOUR='" + TFOUR + '\'' +
                ", N2n='" + N2n + '\'' +
                ", bianma='" + bianma + '\'' +
                '}';
    }

    //    public void setvalue(String name,String value){
//
//        switch (name){
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//            case "": setAp();
//
//        }


//    }

}
