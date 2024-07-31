package com.keyware.MR.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author Yaojz
 * @since 2023-04-25
 */

@TableName("ForwardAnalysis")
public class Forwardanalysis implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id" )
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "工序id" )
    @TableField("EXETIONSTEPID")
    private String exetionstepId;

    @ApiModelProperty(value = "工序" )
    @TableField("EXETIONSTEP")
    private String exetionstep;

    @ApiModelProperty(value = "预调" )
    @TableField("PRESET")
    private String preset;


    @ApiModelProperty(value = "操作方式" )
    @TableField("OPERATION")
    private String operation;

    @ApiModelProperty(value = "工步号" )
    @TableField("PROCESSNUM")
    private String processNum;
    @ApiModelProperty(value = "工步" )
    @TableField("PROCESS")
    private String processs;
    @ApiModelProperty(value = "工步（前端拼接）" )
    @TableField("PROCESSBEFORE")
    private String processBefore;
    @ApiModelProperty(value = "工步调试最小值" )
    @TableField("PROCESSMIN")
    private String processMin;
    @ApiModelProperty(value = "工步调试最大值" )
    @TableField("PROCESSMAX")
    private String processMax;
    @ApiModelProperty(value = "工步调试值单位" )
    @TableField("PROCESSUNIT")
    private String processUnit;
    @ApiModelProperty(value = "输出" )
    @TableField("OUTPUT")
    private String output;
    @ApiModelProperty(value = "输出（前端拼接）" )
    @TableField("OUTPUTBEFORE")
    private String outputBefore;
    @ApiModelProperty(value = "判断方法" )
    @TableField("ESTIMATE")
    private String estimate;
    @ApiModelProperty(value = "输出最小值" )
    @TableField("OUTPUTMIN")
    private String outputMin;
    @ApiModelProperty(value = "输出最大值" )
    @TableField("OUTPUTMAX")
    private String outputMax;
    @ApiModelProperty(value = "输出单位" )
    @TableField("OUTPUTUNIT")
    private String outputUnit;
    @ApiModelProperty(value = "调试方法" )
    @TableField("ADJUSTMENTETHOD")
    private String adjustMentethod;
//    adjust Method


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getExetionstepId() {
        return exetionstepId;
    }

    public void setExetionstepId(String exetionstepId) {
        this.exetionstepId = exetionstepId;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExetionstep() {
        return exetionstep;
    }

    public void setExetionstep(String exetionstep) {
        this.exetionstep = exetionstep;
    }

    public String getProcessNum() {
        return processNum;
    }

    public void setProcessNum(String processNum) {
        this.processNum = processNum;
    }

    public String getProcesss() {
        return processs;
    }

    public void setProcesss(String processs) {
        this.processs = processs;
    }

    public String getProcessBefore() {
        return processBefore;
    }

    public void setProcessBefore(String processBefore) {
        this.processBefore = processBefore;
    }

    public String getProcessMin() {
        return processMin;
    }

    public void setProcessMin(String processMin) {
        this.processMin = processMin;
    }

    public String getProcessMax() {
        return processMax;
    }

    public void setProcessMax(String processMax) {
        this.processMax = processMax;
    }

    public String getProcessUnit() {
        return processUnit;
    }

    public void setProcessUnit(String processUnit) {
        this.processUnit = processUnit;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutputBefore() {
        return outputBefore;
    }

    public void setOutputBefore(String outputBefore) {
        this.outputBefore = outputBefore;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getOutputMin() {
        return outputMin;
    }

    public void setOutputMin(String outputMin) {
        this.outputMin = outputMin;
    }

    public String getOutputMax() {
        return outputMax;
    }

    public void setOutputMax(String outputMax) {
        this.outputMax = outputMax;
    }

    public String getOutputUnit() {
        return outputUnit;
    }

    public void setOutputUnit(String outputUnit) {
        this.outputUnit = outputUnit;
    }

    public String getAdjustMentethod() {
        return adjustMentethod;
    }

    public void setAdjustMentethod(String adjustMentethod) {
        this.adjustMentethod = adjustMentethod;
    }




    @Override
    public String toString() {
        return "Forwardanalysis{" +
                "id=" + id +
                ", exetionstepId='" + exetionstepId + '\'' +
                ", preset='" + preset + '\'' +
                ", exetionstep='" + exetionstep + '\'' +
                ", processNum='" + processNum + '\'' +
                ", operation='" + operation + '\'' +
                ", processs='" + processs + '\'' +
                ", processBefore='" + processBefore + '\'' +
                ", processMin='" + processMin + '\'' +
                ", processMax='" + processMax + '\'' +
                ", processUnit='" + processUnit + '\'' +
                ", output='" + output + '\'' +
                ", outputBefore='" + outputBefore + '\'' +
                ", estimate='" + estimate + '\'' +
                ", outputMin='" + outputMin + '\'' +
                ", outputMax='" + outputMax + '\'' +
                ", outputUnit='" + outputUnit + '\'' +
                ", adjustMentethod='" + adjustMentethod + '\'' +
                '}';
    }
}
