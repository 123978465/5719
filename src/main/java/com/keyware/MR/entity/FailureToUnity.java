package com.keyware.MR.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "故障分析相关数据（提供给unity）")
@Data
public class FailureToUnity implements Serializable {

    @ApiModelProperty(value = "工序")
    private String process;
    @ApiModelProperty(value = "故障动画标识")
    private String modelAnimation;
    @ApiModelProperty(value = "故障工步(预调)")
    private List<String> preSetExecutionStep;
    @ApiModelProperty(value = "测试的工步")
    private List<String>  selectedExecutionStepList;
    @ApiModelProperty(value = "仿真结果")
    private SimulationResult  simulationResult;
    @Override
    public String toString() {
        return "FailureToUnity{" +
                "process='" + process + '\'' +
                ", modelAnimation='" + modelAnimation + '\'' +
                ", preSetExecutionStep=" + preSetExecutionStep +
                ", selectedExecutionStepList=" + selectedExecutionStepList +
                ", simulationResult=" + simulationResult +
                '}';
    }

    public String getModelAnimation() {
        return modelAnimation;
    }

    public void setModelAnimation(String modelAnimation) {
        this.modelAnimation = modelAnimation;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public List<String> getPreSetExecutionStep() {
        return preSetExecutionStep;
    }

    public void setPreSetExecutionStep(List<String> preSetExecutionStep) {
        this.preSetExecutionStep = preSetExecutionStep;
    }

    public List<String> getSelectedExecutionStepList() {
        return selectedExecutionStepList;
    }

    public void setSelectedExecutionStepList(List<String> selectedExecutionStepList) {
        this.selectedExecutionStepList = selectedExecutionStepList;
    }

    public SimulationResult getSimulationResult() {
        return simulationResult;
    }

    public void setSimulationResult(SimulationResult simulationResult) {
        this.simulationResult = simulationResult;
    }
}
