package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("datayuan")
public class TableName1 implements Serializable {

//    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "油门杆角度")
    @TableField("ap")
    private String ap;

    @ApiModelProperty(value = "工步参数最小值")
    @TableField("N1np")
    private String N1;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("N2np")
    private String N2;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("P300a")
    private String P300a;

    //    @ApiModelProperty(value = "工步参数最小值")
//    @TableField("Т4")
//    private String Т4;

    @ApiModelProperty(value = "工步参数最小值")
    @TableField("a1")
    private String а1;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("a2")
    private String а2;

    @ApiModelProperty(value = "工步参数最小值")
    @TableField("GtHz")
    private String GTHz;

    @ApiModelProperty(value = "工步参数最小值")
    @TableField("a2n")
    private String a2n;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("a1n")
    private String a1n;

    @ApiModelProperty(value = "工步参数最小值")
    @TableField("N1n")
    private String N1n;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("N2n")
    private String N2n;
    @ApiModelProperty(value = "工步参数最小值")
    @TableField("PK")
    private String PK;


    @ApiModelProperty(value = "工步参数最小值")
    @TableField("T4")
    private String TFOUR;


    @ApiModelProperty(value = "工步参数最小值")
    @TableField("bianma")
    private String bianma;


    @Override
    public String toString() {
        return "TableName1{" +
                "ap='" + ap + '\'' +
                ", N1='" + N1 + '\'' +
                ", N2='" + N2 + '\'' +
                ", P300a='" + P300a + '\'' +
                ", а1='" + а1 + '\'' +
                ", а2='" + а2 + '\'' +
                ", GTHz='" + GTHz + '\'' +
                ", a2n='" + a2n + '\'' +
                ", a1n='" + a1n + '\'' +
                ", N1n='" + N1n + '\'' +
                ", N2n='" + N2n + '\'' +
                ", PK='" + PK + '\'' +

                ", TFOUR='" + TFOUR + '\'' +
                ", bianma='" + bianma + '\'' +
                '}';
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

    public String getА1() {
        return а1;
    }

    public void setА1(String а1) {
        this.а1 = а1;
    }

    public String getА2() {
        return а2;
    }

    public void setА2(String а2) {
        this.а2 = а2;
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

    public String getN2n() {
        return N2n;
    }

    public void setN2n(String n2n) {
        N2n = n2n;
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

    public String getBianma() {
        return bianma;
    }

    public void setBianma(String bianma) {
        this.bianma = bianma;
    }
}