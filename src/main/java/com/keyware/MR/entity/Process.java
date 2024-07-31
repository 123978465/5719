package com.keyware.MR.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "故障库")
@Data
@TableName("Process")
public class Process {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer ID;
    @ApiModelProperty(value = "工序id")
    @TableField("PROCESS")
    private String process;
    @ApiModelProperty(value = "故障号")
    @TableField("FAULTNUMBER")
    private Integer faultNumber;
    @ApiModelProperty(value = "工步数量")
    @TableField("EXECUTIONSTEPNUM")
    private Integer executionStepNum;
    @ApiModelProperty(value = "(是否需在检测前) 预调 (0 需要，1 不需要)")
    @TableField("PRESET")
    private Integer preset;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Integer getFaultNumber() {
        return faultNumber;
    }

    public void setFaultNumber(Integer faultNumber) {
        this.faultNumber = faultNumber;
    }

    public Integer getExecutionStepNum() {
        return executionStepNum;
    }

    public void setExecutionStepNum(Integer executionStepNum) {
        this.executionStepNum = executionStepNum;
    }

    public Integer getPreset() {
        return preset;
    }

    public void setPreset(Integer preset) {
        this.preset = preset;
    }

    @Override
    public String toString() {
        return "Process{" +
                "ID=" + ID +
                ", process='" + process + '\'' +
                ", faultNumber=" + faultNumber +
                ", executionStepNum=" + executionStepNum +
                ", preset=" + preset +
                '}';
    }
}
